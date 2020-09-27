package com.itheima.test;

import com.itheima.dao.IRoleDao;
import com.itheima.domain.Role;
import com.itheima.mybatis.io.Resources;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;
import com.itheima.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author
 * @Company
 * mybatis的入门案例
 */
public class MybatisTest {

    /**
     * 入门案例
     * @param args
     * 建造者模式和工厂模式的区别：工厂模式注重的是整体对象的创建方法；
     * 建造者模式更关心对象的创建过程，创建对象的调用方法可以在创建时自由调用。
     */
    public static void main(String[] args)throws Exception {
        //1.读取配置文件
        //InputStream in2 =  Resources.getResourceAsStream("com/itheima/dao/IRoleDao.xml");
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        IRoleDao roleDao = session.getMapper(IRoleDao.class);
        //5.使用代理对象执行方法
        List<Role> roles = roleDao.findAll();
        for(Role role : roles){
            System.out.println(role);
        }
        //6.释放资源
        session.close();
        in.close();
    }

    @Test
    public void test01(){
        ThreadLocal tl = new ThreadLocal();
    }
}
