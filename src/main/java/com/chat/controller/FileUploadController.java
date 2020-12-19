package com.chat.controller;

import com.chat.common.annotation.SignVerify;
import com.chat.common.constant.Constant;

import com.chat.common.result.ResultResponse;
import com.chat.pojo.User;
import com.chat.pojo.dto.MinioUploadDto;
import com.chat.pojo.dto.MinioUploadHeaderDto;
import com.chat.pojo.resp.OSSFileResp;
import com.chat.service.BaseService;
import com.chat.service.MinioService;
import com.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2020/3/19
 */

@RequestMapping(value = Constant.API_PATH + "/file", method = RequestMethod.POST)
@Controller
public class FileUploadController extends BaseService {

    @Autowired
    MinioService minioService;
    @Autowired
    UserService userService;

    /**
     * 单文件上传 不需要签名验证
     *
     * @param file
     * @return
     */
    @SignVerify(value = false)
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultResponse<OSSFileResp>> upload(@RequestParam("file") MultipartFile file) {
        try {
            MinioUploadHeaderDto minioUploadDto = minioService.uploadHeader(file);
            User user = getCurrentUser();
            user.setFaceImage(minioUploadDto.getSmaUrl());
            user.setFaceImageBig(minioUploadDto.getBigUrl());
            int code = userService.update(user);
            updateCurrentUser(user);
            OSSFileResp ossFileResp = new OSSFileResp();
            ossFileResp.setFileName(file.getOriginalFilename());
            ossFileResp.setOssFileName(minioUploadDto.getBigUrl());
            return ResponseEntity.ok(ResultResponse.ok(ossFileResp));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(ResultResponse.fail());
        }
    }

    /**
     * 单文件上传 不需要签名验证
     *
     * @param files
     * @return
     */
    @SignVerify(value = false)
    @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResultResponse<List<OSSFileResp>>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            List<OSSFileResp> list = new ArrayList<>();
            for (MultipartFile file : files) {
                MinioUploadDto minioUploadDto = minioService.upload(file);
                OSSFileResp ossFileResp = new OSSFileResp();
                ossFileResp.setFileName(file.getOriginalFilename());
                ossFileResp.setOssFileName(minioUploadDto.getUrl());
                list.add(ossFileResp);
            }
            return ResponseEntity.ok(ResultResponse.ok(list));
        } catch (Exception e) {
            return ResponseEntity.ok(ResultResponse.fail());
        }
    }
}
