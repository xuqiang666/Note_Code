package com.xq.notes.framework.mybatis.dao;

import com.xq.notes.framework.mybatis.domain.User;
import com.xq.notes.framework.mybatis.mybatis.annotations.Select;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
