package com.steve.mapper;

import com.steve.pojo.FriendCircle;
import com.steve.pojo.resp.FriendCircleResp;
import com.steve.utils.mapperUtil.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Steve
 * @date 2020/2/12
 */
public interface FriendCircleMapper extends MyMapper<FriendCircle> {


    List<FriendCircleResp> friendCircles(@Param("userId")String userId);
}
