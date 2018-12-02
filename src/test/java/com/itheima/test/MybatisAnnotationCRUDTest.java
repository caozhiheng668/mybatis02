package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.Account;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisAnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Test
    public void testFindAll(){
        List<User> all = userDao.findAll();
        for (User user : all){
            System.out.println("用户信息----------");
            System.out.println(user);
            System.out.println(user.getAccounts());

        }

        /*User user = new User();
        user.setUsername("aaa");
        int i = userDao.saveUser(user);
        System.out.println("----"+i);
        System.out.println(user);*/
       /* List<User> users = userDao.findByName("%小%");
        for (User user :users){
            System.out.println(user);
        }*/
      /*  List<Account> allAcount = userDao.findAllAcount();
        for (Account account : allAcount){
            System.out.println("账户信息------" +
                    "---------");
            System.out.println(account);
            System.out.println(account.getUser());
        }*/


    }

    @Before//junit 的注解
    public void init()throws Exception{
//1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
//2.创建工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
//3.创建 session
        session = factory.openSession();
//4.创建代理对象
        userDao = session.getMapper(IUserDao.class);
    }
    @After//junit 的注解
    public void destroy()throws Exception {
//提交事务
        session.commit();
//释放资源
        session.close();
//关闭流
        in.close();
    }
}
