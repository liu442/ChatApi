package com.chat.pojo.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 评论列表
 * @author
 * @date 2020/2/14
 */
@Setter
@Getter
public class CommentResp {

    private String id;

    /*发表朋友圈id*/
    private String circleId;

    /*评论者id*/
    private String commentId;

    private String content;

    /*回复别人*/
    private String replyId;

    //类型(1,点赞,2,评论,3回复)
    private Integer type;

    private Date createTime;

    /*昵称*/
    private String nickName;
    /*头像*/
    private String headImage;

    /*回复对象的昵称*/
    private String replyNickName;

    /*1点赞 2取消点赞*/
    private Integer isShowLike;
}
