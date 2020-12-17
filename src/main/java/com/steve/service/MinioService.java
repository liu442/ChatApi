package com.steve.service;

import com.steve.pojo.dto.MinioUploadDto;
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
     * 文件删除
     */
    public void delete(String objectName) throws Exception;
}
