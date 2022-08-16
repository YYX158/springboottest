package com.atyyx.boot05web01.mapper;

import com.atyyx.boot05web01.bean.Emp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.micrometer.core.lang.Nullable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@EnableTransactionManagement // 开启事务管理
public interface CacheMapper extends BaseMapper<Emp>
{
    /**
     * 一。Mybatis是默认开启一级缓存
     * 级别是SqlSeesion，意思就是在相同的缓存中去取数据的时候，就不会在重新去访问数据库了
     * 但是如果是不同的SqlSeesion的话，还是会重新去库中查询
     * 二。Mybatis一级缓存失效的四种情况
     * ①不同的SqlSession
     * ②同一个SqlSession，但是查询的条件不一样
     * ③同一个SqlSeesion两次查询期间，执行了任意多次的增删改操作
     * ④同一个SqlSession两次查询期间手动清理了缓存
     * @param emp
     * @return
     */
    Emp getEmpById(Emp emp);

    /**
     * 任意一次增删改以后都会清空缓存
     * @param emp
     */
    void insertEmp(Emp emp);

    /**
     * 按照性别去查找
     * trim
     * prefix|suffix：将trim标签中内容前面或者后面添加指定的内容元素
     * suffixOverrieds|prefixOverrieds: 将trim标签中内容前面或后面去掉指定内容
     * @return
     */
    List<Emp> selectEmpbyXing(@Param(value = "xing") String xing,Integer PageCurrent,Integer PageSize);


    public int deleteMoreByArray(@Param("eids") Integer [] eids);

    public int insertMoreInsertByList(@Param("emps") List<Emp> emps);

}
