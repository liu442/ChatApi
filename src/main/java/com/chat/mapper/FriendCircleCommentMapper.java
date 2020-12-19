package com.chat.mapper;

import com.chat.pojo.FriendCircleComment;
import com.chat.pojo.resp.CommentResp;
import com.chat.utils.mapperUtil.MyMapper;

import java.util.List;

/**
 * @author
 * @date 2020/2/12
 */
public interface FriendCircleCommentMapper extends MyMapper<FriendCircleComment> {


    List<CommentResp> getComments(FriendCircleComment friendCircleComment);

    List<CommentResp> getLikes(FriendCircleComment friendCircleComment);

}
