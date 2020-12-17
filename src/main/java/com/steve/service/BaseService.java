package com.steve.service;

import com.steve.common.constant.Constant;
import com.steve.pojo.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Steve
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
}
