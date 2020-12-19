package com.chat.service;

import com.chat.common.constant.Constant;
import com.chat.pojo.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date 2020/1/14
 */
public class BaseService {
    protected static User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        if(Constant.API_TOKEN_MAP.get(token)!=null){
            return Constant.API_TOKEN_MAP.get(token);
        }else{
            User user=new User();
            user.setId("-1");
            return user;
        }
    }
    //更新当前角色信息
    protected static void updateCurrentUser(User user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        Constant.API_TOKEN_MAP.put(token, user);
    }
}
