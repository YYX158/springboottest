package com.atyyx.boot05web01;

import com.atyyx.boot05web01.mapper.CacheMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CacheMapperTest {

    @Autowired
    CacheMapper cacheMapper;
    @Test
    public void test()
    {
        //Emp emp = cacheMapper.getEmpById();
        //log.info("将要展示的是："+emp.toString());
    }
}
