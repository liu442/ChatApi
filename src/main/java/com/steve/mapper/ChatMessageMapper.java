package com.steve.mapper;

import com.steve.pojo.ChatMsg;
import com.steve.utils.mapperUtil.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Steve
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
