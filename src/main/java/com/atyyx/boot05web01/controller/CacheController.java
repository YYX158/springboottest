package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.bean.Emp;
import com.atyyx.boot05web01.mapper.CacheMapper;
import com.atyyx.boot05web01.service.CacheService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

@RestController
public class CacheController {
    @Autowired
    CacheService cacheService;

    @GetMapping("/getEmpById")
    public Emp getEmpById()
    {
        Emp pEmp = new Emp(2, "王赛", 22, "男", "wangsai@qq.com");
        Emp emp1 = cacheService.getEmpById(pEmp);

        return  emp1;
    }

    @GetMapping("/insertEmp")
    public void InsertEmp()
    {
        Emp emp = new Emp(5, "张三", 18, "女", "zhangsan@xinna.com");
        cacheService.setAemp(emp);
    }

    @GetMapping("/getEmpsbyXing/{pid}/{psize}")
    public List<Emp> getEmpsbyXing(@PathVariable("pid") String pageCurrent1,
                                   @PathVariable("psize")String psize1) throws IOException {
        String xing="王";

        //System.out.println("当前页面是"+pageCurrent1);
        String[] split = pageCurrent1.split("=");
        Integer pageCurrent=pageCurrent1.indexOf("=")!=-1?Integer.parseInt(split[1]):Integer.parseInt(pageCurrent1);
        //Integer tempCurrent=Integer.parseInt(pageCurrent1);


        String[] split1 = psize1.split("=");
        Integer pageSize = psize1.indexOf("=")!=-1?Integer.parseInt(split1[1]):Integer.parseInt(psize1);
        // 查询数据
        List<Emp> emps = cacheService.getEmpByXing(xing,pageCurrent,pageSize);
        emps.stream().forEach(emp -> System.out.println(emp));
        return emps;
    }

    @GetMapping("/deleteEmpByIntegers")
    public Integer deleteEmpByIntegers()
    {
        return cacheService.deleteMoreByArray(new Integer[]{7,8,9,10,11});
    }

    @GetMapping("/insertEmps")
    public Integer insertEmps()
    {
        Emp emp1 = new Emp(null, "123", 23, "男", "123@stu.xujc.com");
        Emp emp2 = new Emp(null, "456", 35, "男", "456@stu.xujc.com");
        Emp emp3 = new Emp(null, "789", 15, "女", "789@stu.xujc.com");
        Emp emp4 = new Emp(null, "012", 85, "男", "012@stu.xujc.com");
        Emp emp5 = new Emp(null, "345", 89, "女", "345@stu.xujc.com");
        return cacheService.InsertEmps(Arrays.asList(emp1,emp2,emp3,emp4,emp5));
    }

    //SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionFactory);
    // 关闭会话sqlSession.close();
    //sqlSession.clearCache();// 清除缓存
    //sqlSession.commit();//提交事务
    //当我们关闭sqlSession以后，数据才会被保存到二级缓存中
    //要想开启二级缓存，实体类就必须实现这个接口Serializable
    @GetMapping("/testTwoCache")
    public List<Emp> testTwoCache()
    {
        // 创建一个工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory() {
            @Override
            public SqlSession openSession() {
                return null;
            }

            @Override
            public SqlSession openSession(boolean b) {
                return null;
            }

            @Override
            public SqlSession openSession(Connection connection) {
                return null;
            }

            @Override
            public SqlSession openSession(TransactionIsolationLevel transactionIsolationLevel) {
                return null;
            }

            @Override
            public SqlSession openSession(ExecutorType executorType) {
                return null;
            }

            @Override
            public SqlSession openSession(ExecutorType executorType, boolean b) {
                return null;
            }

            @Override
            public SqlSession openSession(ExecutorType executorType, TransactionIsolationLevel transactionIsolationLevel) {
                return null;
            }

            @Override
            public SqlSession openSession(ExecutorType executorType, Connection connection) {
                return null;
            }

            @Override
            public Configuration getConfiguration() {
                return null;
            }
        };
        // 创建一个SqlSession
        //即使不是同一个SqlSeesion，但是由于他们都是由同一个sqlSessionFactory制造的
        //所以他们可以从二级缓存中读取数据

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        //获取mapper对象

        CacheMapper cacheMapper1 = sqlSession1.getMapper(CacheMapper.class);
        CacheMapper cacheMapper2 = sqlSession2.getMapper(CacheMapper.class);

        Emp pEmp = new Emp(2, "王赛", 22, "男", "wangsai@qq.com");

        Emp emps1 = cacheMapper1.getEmpById(pEmp);
        Emp emps2 = cacheMapper2.getEmpById(pEmp);

        return Arrays.asList(emps1,emps2);
        //
    }
}
