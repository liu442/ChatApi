package com.steve.controller;

import com.steve.common.constant.Constant;
import com.steve.common.result.ResultResponse;
import com.steve.pojo.ChatMsg;
import com.steve.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 聊天信息记录
 * @author Steve
 * @date 2020/2/2
 */
@RestController
@RequestMapping(value = Constant.API_PATH+"/chatMessage",method = RequestMethod.POST)
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping("/getChatMessage")
    @ResponseBody
    public ResponseEntity<ResultResponse<List<ChatMsg>>> getChatMessage(@RequestBody ChatMsg chatMsg){
        return ResponseEntity.ok(chatMessageService.getChatMessage(chatMsg));
    }
}
