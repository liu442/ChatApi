package com.chat.mapper;

import com.chat.pojo.FriendCircle;
import com.chat.pojo.resp.FriendCircleResp;
import com.chat.utils.mapperUtil.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 * @date 2020/2/12
 */
public interface FriendCircleMapper extends MyMapper<FriendCircle> {


    List<FriendCircleResp> friendCircles(@Param("userId")String userId);
}
