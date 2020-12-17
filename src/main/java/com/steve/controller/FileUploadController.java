package com.steve.controller;

import com.steve.common.annotation.SignVerify;
import com.steve.common.constant.Constant;

import com.steve.common.result.ResultResponse;
import com.steve.pojo.dto.MinioUploadDto;
import com.steve.pojo.resp.OSSFileResp;
import com.steve.service.MinioService;
import com.steve.utils.systemUtil.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steve
 * @date 2020/3/19
 */

@RequestMapping(value = Constant.API_PATH+"/file",method = RequestMethod.POST)
@Controller
public class FileUploadController {

    @Autowired
    MinioService minioService;

    /**
     * 单文件上传 不需要签名验证
     * @param file
     * @return
     */
    @SignVerify(value = false)
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultResponse<OSSFileResp>> upload(@RequestParam("file") MultipartFile file){
        try {
            MinioUploadDto minioUploadDto = minioService.upload(file);
            OSSFileResp ossFileResp= new OSSFileResp();
            ossFileResp.setFileName(file.getOriginalFilename());
            ossFileResp.setOssFileName(minioUploadDto.getUrl());
            return ResponseEntity.ok(ResultResponse.ok(ossFileResp));
        }catch (Exception e){
            return ResponseEntity.ok(ResultResponse.fail());
        }
    }

    /**
     * 单文件上传 不需要签名验证
     * @param files
     * @return
     */
    @SignVerify(value = false)
    @RequestMapping(value="/uploadFiles",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultResponse<List<OSSFileResp>>> uploadFiles(@RequestParam("files") MultipartFile[] files){
        try {
            List<OSSFileResp> list = new ArrayList<>();
            for (MultipartFile file: files) {
                MinioUploadDto minioUploadDto = minioService.upload(file);
                OSSFileResp ossFileResp= new OSSFileResp();
                ossFileResp.setFileName(file.getOriginalFilename());
                ossFileResp.setOssFileName(minioUploadDto.getUrl());
                list.add(ossFileResp);
            }
            return ResponseEntity.ok(ResultResponse.ok(list));
        }catch (Exception e){
            return ResponseEntity.ok(ResultResponse.fail());
        }
    }
}
