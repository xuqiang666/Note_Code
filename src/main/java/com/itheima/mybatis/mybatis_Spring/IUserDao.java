package com.itheima.mybatis.mybatis_Spring;

/**
 * @Author: 许庆之 on 2020/10/23.
 */
public interface IUserDao {

    @Select("select userName from user where id = #{uId}")
    String queryUserInfo(String uId);

}
