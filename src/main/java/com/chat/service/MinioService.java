package com.chat.service;

import com.chat.pojo.dto.MinioUploadDto;
import com.chat.pojo.dto.MinioUploadHeaderDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * minio文件上传
 */
public interface MinioService {

    /**
     * 文件上传
     * @param file
     * @return
     */
    public MinioUploadDto upload(MultipartFile file) throws Exception;
    /**
     * 头像上传(包含缩略图80x80)
     * @param file
     * @return
     */
    public MinioUploadHeaderDto uploadHeader(MultipartFile file) throws Exception;

    /**
     * 文件删除
     */
    public void delete(String objectName) throws Exception;
}
