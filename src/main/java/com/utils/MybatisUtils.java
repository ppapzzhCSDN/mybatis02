package com.utils;

import javafx.beans.value.WritableDoubleValue;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Reader;

/**
 * @author zzh
 * @description
 * @date
 */
public class MybatisUtils {

    //构建sqlSession的工厂，地位等同于jdbc的connection。
    //同时也是一个重量级的对象，不能频繁的创建，数量也要有限制
    //注意：基于如果出现多个会话工厂，可能导致事务不同步的问题，所以我们一个项目只使用一个会话工厂
    public static final SqlSessionFactory SSF = createSqlSessionFactory();

    // 需求：实现同一条线程，使用的session是相同的。线程绑定,
    // 创建一个线程绑定变量，将变量放在该对象里面，表示对象同一条线程共享
    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal();

    // 创建会话工厂SqlSessionFactory
    private static SqlSessionFactory createSqlSessionFactory() {
        try {
            // 返回一个Reader对象
            Reader reader = Resources.getResourceAsReader("mybatis-cfg.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sessionFactory = builder.build(reader);
            return sessionFactory;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //创建能执行映射文件中sql的sqlSession，等同于jdbc中的preparestatment对象
    public static SqlSession getSqlSession() {
        //1.判断线程变量里面是否已经有值
        if (threadLocal.get() == null) {
            //2.如果没有值，那么创建一个session对象设置在线程变量里面
            SqlSession session = SSF.openSession();
            threadLocal.set(session);
        }
        //3.返回线程变量里面的session对象
        return threadLocal.get();
    }

    public static void main(String[] args) {
        //同一条线程，对象一样
        //注意事项：如果对象是一个静态的属性的，意味着，整个系统共用一个对象
        //如果放在ThreadLocal对象里面，仅仅是当前线程共用该对象
        System.out.println(MybatisUtils.getSqlSession());
        System.out.println(MybatisUtils.getSqlSession());
    }

}