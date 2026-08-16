package com.xq.notes.framework.mybatis.mybatis.sqlsession;

/**
 * @author
 */
public interface SqlSessionFactory {

    /**
     * 用于打开一个新的SqlSession对象
     * @return
     */
    SqlSession openSession();
}
