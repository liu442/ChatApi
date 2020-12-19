package com.chat.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Table(name = "my_friends")
public class MyFriends {

    /**
     * 用户id
     */
    @Column(name = "my_user_id")
    private String myUserId;

    /**
     * 用户的好友id
     */
    @Column(name = "my_friend_user_id")
    private String myFriendUserId;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}