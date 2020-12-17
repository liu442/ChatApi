package com.steve.mapper;

import com.steve.pojo.FriendCircleComment;
import com.steve.pojo.resp.CommentResp;
import com.steve.utils.mapperUtil.MyMapper;

import java.util.List;

/**
 * @author Steve
 * @date 2020/2/12
 */
public interface FriendCircleCommentMapper extends MyMapper<FriendCircleComment> {


    List<CommentResp> getComments(FriendCircleComment friendCircleComment);

    List<CommentResp> getLikes(FriendCircleComment friendCircleComment);

}
