package com.chat.utils.mapperUtil;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author
 * @date 2019/8/16
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
