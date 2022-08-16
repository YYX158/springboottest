package com.atyyx.boot05web01.service.impl;

import com.atyyx.boot05web01.bean.Emp;
import com.atyyx.boot05web01.mapper.CacheMapper;
import com.atyyx.boot05web01.service.CacheService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CacheServiceImpl extends ServiceImpl<CacheMapper, Emp> implements CacheService {


    @Autowired
    CacheMapper cacheMapper;
    @Override
    public Emp getEmpById(@Param("emp") Emp emp) {
        return cacheMapper.getEmpById(emp);
    }

    @Override
    public void setAemp(@Param("emp") Emp emp) {
        cacheMapper.insertEmp(emp);
    }

    @Override
    public List<Emp> getEmpByXing(String xing,Integer pageCurrent,Integer pageSize) throws IOException {
//        // 1.获取核心配置文件的输入输出流
//        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
//        // 2.获取sqlSessionFactoryBuilder对象
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//        //3.获取sqlSessionFactory对象
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);// 将核心配置文件放入
//        // 4.创建sql会话对象sqlSession，是Mybatis提供的操作数据库的对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //5.创建类的代理实现类对象
//        CacheMapper cacheMapper = sqlSession.getMapper(CacheMapper.class);
//        // 执行sql操作
//        cacheMapper.selectEmpbyXing(xing,pageCurrent,pageSize);
//        // 提交事务，默认是rollback
//        sqlSession.commit();
//        // 关闭sqlSession
//        sqlSession.close();


        return this.cacheMapper.selectEmpbyXing(xing,pageCurrent,pageSize);
    }

    @Override
    public int deleteMoreByArray(Integer[] eids) {
        return cacheMapper.deleteMoreByArray(eids);
    }

    @Override
    public Integer InsertEmps(List<Emp> emps) {
        return cacheMapper.insertMoreInsertByList(emps);
    }


}
