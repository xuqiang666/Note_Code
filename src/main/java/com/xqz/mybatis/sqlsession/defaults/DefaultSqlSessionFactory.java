package com.xqz.mybatis.sqlsession.defaults;

import com.xqz.mybatis.cfg.Configuration;
import com.xqz.mybatis.sqlsession.SqlSession;
import com.xqz.mybatis.sqlsession.SqlSessionFactory;


/**
 * @author
 * SqlSessionFactory接口的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    /**
     * 用于创建一个新的操作数据库对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
