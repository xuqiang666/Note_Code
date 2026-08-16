package com.xq.notes.framework.mybatis.mybatis.sqlsession;

import com.xq.notes.framework.mybatis.mybatis.cfg.Configuration;
import com.xq.notes.framework.mybatis.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.xq.notes.framework.mybatis.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @author
 *  用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来构建一个SqlSessionFactory工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return  new DefaultSqlSessionFactory(cfg);
    }
}
