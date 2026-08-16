package com.xq.notes.framework.mybatis.dao;

import com.xq.notes.framework.mybatis.domain.Role;
import com.xq.notes.framework.mybatis.mybatis.annotations.Select;

import java.util.List;

/**
 * create by 许庆之 on 2020/3/14.
 */
public interface IRoleDao {

    @Select("select * from role")
    public List<Role> findAll();
}
