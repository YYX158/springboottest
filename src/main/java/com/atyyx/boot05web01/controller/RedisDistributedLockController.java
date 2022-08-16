package com.atyyx.boot05web01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisDistributedLockController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/testLock")
    public void  testLock()
    {
        //1.获取锁 setnx
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        // 设置值3s以内会过期
        Boolean lock = operations.setIfAbsent("lock", "1111",3, TimeUnit.SECONDS);// 类似于setnx
        if(lock)
        {
            String uuid= UUID.randomUUID().toString();//随机生成一个值
            String num = operations.get("num");
            if(num.isEmpty())
                return ;
            // 如果有值就将值转换成Integer类型
            int numInt = Integer.parseInt(num);
            ++numInt;
            operations.set("num", Integer.toString(numInt));
            // 使用lua脚本来锁
            String script="if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
            // 使用redis执行  lua执行
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(script);
            // 设置一下返回值类型  为Long
            //因为删除判断的时候，返回的是0，给起封装为数据类型；如果不封装，那么默认返回String类型
            // 那么返回字符串与0会发生错误
            redisScript.setResultType(Long.class);

            String lock1 = operations.get("lock");
            if(lock1.equals(uuid))// 只有是你自己的，才能删除
            {
                // 第一个是要scirpt脚本，第二个是需要判断的key  第三个是key对应的值
                // 使用lua脚本进行原子操作（删除）
                //stringRedisTemplate.execute(redisScript, Arrays.asList(lock),uuid);
                //4.释放锁
                stringRedisTemplate.delete("lock");
            }

        }
        else
        {
            //3.获取锁失败了，每隔0.1s的时候在来取
            try
            {
                Thread.sleep(100);
                testLock();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
