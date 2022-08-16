package com.atyyx.boot05web01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@Slf4j
public class RedisController
{
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    //@Autowired
    //RedisTemplate<String,Object> redisTemplate;




    @GetMapping("/StringLookRedis")
    public String StringLookRedis()
    {
        // 相当于要操作<key,value>都是String类型的
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("hello","world");
        String hello = operations.get("k1");
        System.out.println("hello对应的值是:"+hello);

        //查看连接工厂
        Class<? extends RedisConnectionFactory> aClass = redisConnectionFactory.getClass();
        System.out.println("连接工厂是"+aClass.toString());
        return hello;
    }

    @GetMapping("/addList")
    public String testOtherElementRedis()
    {
//        ValueOperations operations = redisTemplate.opsForValue();
//        //operations.set("zhangsan","张三");
//        Object zhangsan = operations.get("hello");
//        return zhangsan.toString();

        // 字符串操作
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object k5 = valueOperations.get("k5");
        // 列表操作
        // 获取所有元素
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        //List<String> k8 = opsForList.range("k8", 0, -1);// 相当于就是lrange k8 0 -1


        String k81 = opsForList.index("k8", 2);// 相当于 lindex k8 2  获取第三个位置的值

        //从左边添加一个新的元素
        opsForList.leftPushAll("k8","6");
        // 弹出最左边的2元素
        //opsForList.leftPop("k8",2);
        //删掉count个指定元素
        opsForList.remove("k8",2,"6");  // 相当于 lrem key count value  删掉count个value
        List<String> k8 = opsForList.range("k8", 0, -1);// 相当于就是lrange k8 0 -1


        return k8.toString();


    }

}
