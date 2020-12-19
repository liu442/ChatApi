package com.chat.controller;


import com.chat.common.annotation.SignVerify;
import com.chat.common.constant.Constant;
import com.chat.common.result.ResultResponse;
import com.chat.pojo.User;
import com.chat.pojo.dto.MinioUploadHeaderDto;
import com.chat.service.BaseService;
import com.chat.service.MinioService;
import com.chat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author
 * @date 2019/8/15
 */
@RestController
@RequestMapping(value = Constant.API_PATH + "/user", method = RequestMethod.POST)
public class UserController extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private  MinioService minioService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity<ResultResponse> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @RequestMapping("/getInfo")
    @ResponseBody
    public ResponseEntity<ResultResponse> getInfo() {
        return ResponseEntity.ok(userService.getInfo());
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public ResponseEntity<ResultResponse<List<User>>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * 获取联系人列表
     *
     * @return
     */
    @RequestMapping(value = "/getMyFriends")
    @ResponseBody
    public ResponseEntity<ResultResponse<List<User>>> getMyFriends() {
        return ResponseEntity.ok(userService.getMyFriends());
    }
}
