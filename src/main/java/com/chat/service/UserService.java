package com.chat.service;

import com.chat.common.constant.Constant;
import com.chat.common.result.ResultCode;
import com.chat.common.result.ResultResponse;
import com.chat.mapper.MyFriendsMapper;
import com.chat.mapper.UserMapper;
import com.chat.pojo.User;
import com.chat.utils.systemUtil.MD5Utils;
import com.chat.utils.systemUtil.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author
 * @date 2019/8/15
 */
@Service
public class UserService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MyFriendsMapper myFriendsMapper;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    public ResultResponse login(User user) {
        //判断用户名是否存在
        User userExist = new User();
        userExist.setUsername(user.getUsername());
        User resultExist = userMapper.selectOne(userExist);
        if (resultExist == null) {
            return ResultResponse.fail(ResultCode.USER_NOT_EXIST);
        }
        //用户名密码登录
        Example userExample = new Example(User.class);
        Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        String password = MD5Utils.MD532(user.getPassword(), resultExist.getId());
        criteria.andEqualTo("password", password);
        User loginResult = userMapper.selectOneByExample(userExample);
        if (loginResult == null) {
            //用户名或者密码错误
            logger.info("用户" + user.getUsername() + "登录失败！");
            return ResultResponse.fail(ResultCode.USER_PASS_ERROR);
        }
        logger.info("用户" + user.getUsername() + "登录成功！");
        // 现在没有做 token 先返回用户id 给前端存储
        User userRes = new User();
        userRes.setId(resultExist.getId());
        userRes.setFaceImageBig(resultExist.getFaceImageBig());
        userRes.setFaceImage(resultExist.getFaceImage());
        userRes.setUsername(resultExist.getUsername());
        userRes.setNickname(resultExist.getNickname());
        userRes.setToken(UUIDUtils.getUUID32());
        userRes.setSignKey(UUIDUtils.getUUID32());
        Constant.API_TOKEN_MAP.put(userRes.getToken(), userRes);
        return ResultResponse.ok(userRes);
    }


    public ResultResponse<List<User>> getMyFriends() {
        User currentUser = getCurrentUser();
        List<User> myfriends = userMapper.getMyfriends(currentUser.getId());
        return ResultResponse.ok(myfriends);
    }


    public ResultResponse<List<User>> getUsers() {
        return ResultResponse.ok(userMapper.selectAll());
    }

    public ResultResponse<User> getInfo(){
        User user = getCurrentUser();
        return ResultResponse.ok(user);
    };

    public int update(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
