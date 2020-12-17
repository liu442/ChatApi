package com.steve.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.PageHelper;
import com.steve.common.result.ResultResponse;
import com.steve.mapper.ChatMessageMapper;
import com.steve.pojo.ChatMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Steve
 * @date 2020/2/2
 */
@Service
public class ChatMessageService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private ChatMessageMapper chatMessageMapper;

    /**
     * 新增聊天信息
     * @return
     */
    public ResultResponse addChatMessage(ChatMsg chatMsg){
        try {
            logger.info("新增聊天记录:"+chatMsg.toString());
            chatMessageMapper.insert(chatMsg);
            return ResultResponse.ok();
        }catch (Exception e){
            logger.info("新增聊天记录:{},异常信息:{}",chatMsg.toString(),e);
            return ResultResponse.fail();
        }

    }

    /**
     * 分页查询聊天记录
     * @param chatMsg
     * @return
     */
    public ResultResponse<List<ChatMsg>> getChatMessage(ChatMsg chatMsg){
        chatMsg.setSendUserId(getCurrentUser().getId());
        PageHelper.startPage(chatMsg.getPage(),chatMsg.getPageSize(),"create_time DESC");
        try {
            logger.info("查询聊天记录:{}",chatMsg.toString());
            List<ChatMsg> chatMsgs = chatMessageMapper.getChatMessage(chatMsg);
            return ResultResponse.ok(chatMsgs);
        }catch (Exception e){
            logger.info("查询聊天记录:{},异常信息:{}",chatMsg.toString(),e);
            return ResultResponse.fail();
        }
    }
}
