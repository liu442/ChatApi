package com.steve.service;

import com.steve.common.constant.Constant;
import com.steve.common.result.ResultCode;
import com.steve.common.result.ResultResponse;
import com.steve.mapper.MyFriendsMapper;
import com.steve.mapper.UserMapper;
import com.steve.pojo.MyFriends;
import com.steve.pojo.User;
import com.steve.utils.systemUtil.MD5Utils;
import com.steve.utils.systemUtil.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Steve
 * @date 2019/8/15
 */
@Service
public class UserService extends BaseService{
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyFriendsMapper myFriendsMapper;

    /**
     * 登录
     * @param user
     * @return
     */
    public ResultResponse login(User user){
        //判断用户名是否存在
        User userExist = new User();
        userExist.setUsername(user.getUsername());
        User resultExist = userMapper.selectOne(userExist);
        if(resultExist==null){
            return ResultResponse.fail(ResultCode.USER_NOT_EXIST);
        }
        //用户名密码登录
        Example userExample = new Example(User.class);
        Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        String password = MD5Utils.MD532(user.getPassword(),resultExist.getId());
        criteria.andEqualTo("password", password);
        User loginResult = userMapper.selectOneByExample(userExample);
        if(loginResult==null){
            //用户名或者密码错误
            logger.info("用户"+user.getUsername()+"登录失败！");
            return ResultResponse.fail(ResultCode.USER_PASS_ERROR);
        }
        logger.info("用户"+user.getUsername()+"登录成功！");
        // 现在没有做 token 先返回用户id 给前端存储
        User userRes = new User();
        userRes.setId(resultExist.getId());
        userRes.setUsername(resultExist.getUsername());
        userRes.setNickname(resultExist.getNickname());
        userRes.setToken(UUIDUtils.getUUID32());
        userRes.setSignKey(UUIDUtils.getUUID32());
        Constant.API_TOKEN_MAP.put(userRes.getToken(), userRes);
        return ResultResponse.ok(userRes);
    }


    public ResultResponse<List<User>> getMyFriends(){
        User currentUser = getCurrentUser();
        List<User> myfriends = userMapper.getMyfriends(currentUser.getId());
        return ResultResponse.ok(myfriends);
    }


    public ResultResponse<List<User>> getUsers(){
        return ResultResponse.ok(userMapper.selectAll());
    }

}
