package com.steve.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 用户名，账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 我的头像，如果没有默认给一张
     */
    @Column(name = "face_image")
    private String faceImage;

    @Column(name = "face_image_big")
    private String faceImageBig;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 新用户注册后默认后台生成二维码，并且上传到fastdfs
     */
    private String qrcode;

    private String cid;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createtime;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;


    /**
     * 返回给前端的 token @Transient标识非数据库字段
     */
    @Transient
    private String token;

    /**
     * 返回给前端一个随机的签名key
     */
    @Transient
    private String signKey;

}