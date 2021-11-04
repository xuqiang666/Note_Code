package com.xqz.dao;

import com.xqz.domain.Role;
import com.xqz.mybatis.annotations.Select;

import java.util.List;

/**
 * create by 许庆之 on 2020/3/14.
 */
public interface IRoleDao {

    @Select("select * from role")
    public List<Role> findAll();
}
