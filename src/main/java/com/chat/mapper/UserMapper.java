package com.chat.mapper;


import com.chat.pojo.User;
import com.chat.utils.mapperUtil.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author
 * @date 2019/8/15
 */
public interface UserMapper extends MyMapper<User> {

    /**
     * 查询联系人列表
     * @return
     */
    List<User> getMyfriends(@Param("myUserId") String id);

}
