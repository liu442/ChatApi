package com.chat.pojo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 朋友圈内容
 * @author
 * @date 2020/2/12
 */
@Setter
@Getter
@Table(name = "friend_circle")
public class FriendCircle extends Page{
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "content")
    private String content;

    @Transient
    private List<String> files;

    @Column(name = "image")
    private String image;

    @Column(name = "location")
    private String location;

    @Column(name = "create_time")
    private Date createTime;
}
