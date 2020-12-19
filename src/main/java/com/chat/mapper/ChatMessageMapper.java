package com.chat.mapper;

import com.chat.pojo.ChatMsg;
import com.chat.utils.mapperUtil.MyMapper;

import java.util.List;

/**
 * @author
 * @date 2020/2/2
 */
public interface ChatMessageMapper extends MyMapper<ChatMsg> {

    /**
     * 分页查询聊天历史记录
     * @param chatMsg
     * @return
     */
    List<ChatMsg> getChatMessage(ChatMsg chatMsg);
}
