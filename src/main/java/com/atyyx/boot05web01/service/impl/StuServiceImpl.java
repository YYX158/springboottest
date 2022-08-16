package com.atyyx.boot05web01.service.impl;

import com.atyyx.boot05web01.mapper.StuMapper;
import com.atyyx.boot05web01.bean.Stu;
import com.atyyx.boot05web01.service.StuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuServiceImpl extends ServiceImpl<StuMapper,Stu> implements StuService
{

    @Autowired
    StuMapper stuMapper;

    Counter counter;

    public StuServiceImpl(MeterRegistry meterRegistry)
    {
        // 参数就是指标的名称
        counter = meterRegistry.counter("StuService.getStuById.cout");

    }

    public Stu getStuById(long id)
    {
        counter.increment(); // 增加调用次数
        return stuMapper.getStuById(id);
    }


    // 也可以使用下面这种方法来监控指标
//    @Bean
//    MeterBinder queueSize(Queue queue)
//    {
//        return (registry)-> Gauge.builder("queueSize",queue::size).register(registry);
//    }


}
