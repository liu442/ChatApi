package com.chat.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @date 2019/8/15
 */
@Setter
@Getter
@Table(name ="tb_user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class TbUser implements Serializable {
    private static final long serialVersionUID = -2949129657250436888L;
    @Id
    @Column(name="user_id")
    private String userId;

    /**
     * 用户名
     */
    @Column(name="user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name="user_pass")
    private String userPass;

    /**
     * 手机号
     */
    @Column(name="user_phone")
    private String userPhone;

    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;

    /**
     *  表示该属性不是表中的列
     *  token
     */
//    @Transient
//    private String token;
}
