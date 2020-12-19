package com.chat.controller;

import com.chat.common.constant.Constant;
import com.chat.common.result.ResultResponse;
import com.chat.pojo.ChatMsg;
import com.chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 聊天信息记录
 * @author
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
