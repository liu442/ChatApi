package com.chat.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  评论点赞表
 * @author
 * @date 2020/2/14
 */
@Setter
@Getter
@Table(name = "friend_circle_comment")
public class FriendCircleComment {

    @Id
    @Column(name = "id")
    private String id;

    /*发表朋友圈id*/
    @Column(name = "circle_id")
    private String circleId;

    /*评论者id*/
    @Column(name = "comment_id")
    private String commentId;

    @Column(name = "content")
    private String content;

    /*回复别人*/
    @Column(name = "reply_id")
    private String replyId;

//    类型(1,点赞,2,评论,3回复)
    @Column(name = "type")
    private Integer type;

    @Column(name = "create_time")
    private Date createTime;
}
