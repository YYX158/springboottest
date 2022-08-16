package com.atyyx.boot05web01.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtils
{
    private static SqlSession sqlSession=null;
    public static SqlSession getSqlSession() throws IOException {
        // 1.创建一个输入输出流
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        // 2.创建一个SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 3.通过SqlSessionFactory工厂去创建SqlSession 对象  true表示自动提交事务
        sqlSession = sqlSessionFactory.openSession(true);
        return sqlSession;
    }
}
