package com.chat.common.constant;

import com.chat.pojo.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @date 2020/1/14
 */
public class Constant {
    /**
     * 对外开放的api接口前缀
     */
    public static final String API_PATH = "api";


    /**
     * API TOKEN 存储 map 键值对 根据token 获取用户信息
     */
    public static Map<String, User> API_TOKEN_MAP = new ConcurrentHashMap<String, User>();
}
