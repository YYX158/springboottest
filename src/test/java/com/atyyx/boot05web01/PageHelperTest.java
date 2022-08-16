package com.atyyx.boot05web01;

import com.atyyx.boot05web01.bean.Emp;
import com.atyyx.boot05web01.bean.Mbg;
import com.atyyx.boot05web01.mapper.CacheMapper;
import com.atyyx.boot05web01.mapper.MbgMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DisplayName("分页功能测试")
public class PageHelperTest {

    @Autowired
    CacheMapper cacheMapper;

    /**
     * limit index,pageSize
     * index: 当前页的起始索引
     * pageSize: 每页显示的条数
     * pageNum:当前页的页码
     * index=(pageNum-1)*pageSize
     */
    @Test
    public void testPageHelper()
    {
        //List<Emp> emps = cacheMapper.selectEmpbyXing("王",);
        //System.out.println(emps.isEmpty());
    }
}
