package com.steve.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.steve.pojo.dto.BucketPolicyConfigDto;
import com.steve.pojo.dto.MinioUploadDto;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class MinioServiceImpl implements MinioService {

    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @Override
    public MinioUploadDto upload(MultipartFile file) throws Exception {
        //创建一个MinIO的Java客户端
        MinioClient minioClient = getMinioClient();
        String filename = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 设置存储对象名称
        String uuid = IdUtil.randomUUID();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sdf.format(new Date())).append("/").append(uuid).append(".").append(FileUtil.extName(filename));
        String objectName = stringBuilder.toString();
        // 使用putObject上传一个文件到存储桶中
        return getMinioUploadDto(file, minioClient, objectName);
    }

    private MinioUploadDto getMinioUploadDto(MultipartFile file, MinioClient minioClient, String objectName) throws IOException, ErrorResponseException, InsufficientDataException, InternalException, InvalidBucketNameException, InvalidKeyException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException {
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .object(objectName)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
        minioClient.putObject(putObjectArgs);
        log.info("文件上传成功!");
        MinioUploadDto minioUploadDto = new MinioUploadDto();
        minioUploadDto.setName(objectName);
        minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
        return minioUploadDto;
    }

    private MinioClient getMinioClient() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidBucketNameException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException, RegionConflictException {
        //创建一个MinIO的Java客户端
        MinioClient minioClient = MinioClient.builder()
                .endpoint(ENDPOINT)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
        if (isExist) {
            log.info("存储桶已经存在！");
        } else {
            //创建存储桶并设置只读权限
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(BUCKET_NAME);
            SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                    .bucket(BUCKET_NAME)
                    .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                    .build();
            minioClient.setBucketPolicy(setBucketPolicyArgs);
        }
        return minioClient;
    }

    @Override
    public void delete(String objectName) throws Exception {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(ENDPOINT)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(objectName).build());
    }

    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::" + bucketName + "/*.**").build();
        return BucketPolicyConfigDto.builder()
                .Version("2012-10-17")
                .Statement(CollUtil.toList(statement))
                .build();
    }
}
