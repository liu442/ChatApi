package com.chat.pojo.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2020/2/13
 */
@Setter
@Getter
public class FriendCircleResp {

    private String id;

    private String userId;

    private String content;

    private String image;

    private String location;

    private Date createTime;


    private String nickName;

    /*头像*/
    private String headImage;

    /*评论列表*/
    private List<CommentResp> comments;

    /*点赞列表*/
    private List<CommentResp> like;

    /*当前登录人是否喜欢*/
    private Integer currentUserIsLike;
}
