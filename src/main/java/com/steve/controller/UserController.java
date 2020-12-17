package com.steve.controller;


import com.steve.common.constant.Constant;
import com.steve.common.result.ResultResponse;
import com.steve.pojo.TbUser;
import com.steve.pojo.User;
import com.steve.service.BaseService;
import com.steve.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Steve
 * @date 2019/8/15
 */
@RestController
@RequestMapping(value = Constant.API_PATH+"/user",method = RequestMethod.POST)
public class UserController extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity<ResultResponse> login(@RequestBody User user){
        return ResponseEntity.ok(userService.login(user));
    }


    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public ResponseEntity<ResultResponse<List<User>>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * 获取联系人列表
     * @return
     */
    @RequestMapping(value = "/getMyFriends")
    @ResponseBody
    public ResponseEntity<ResultResponse<List<User>>> getMyFriends(){
        return ResponseEntity.ok(userService.getMyFriends());
    }
}
