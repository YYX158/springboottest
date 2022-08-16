package com.atyyx.boot05web01.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    //volatile是一个特征修饰符（type specifier）.
    // volatile的作用是作为指令关键字，
    // 确保本条指令不会因编译器的优化而省略，
    // 且要求每次直接读值。
    private static volatile JedisPool jedisPool;
    public JedisPoolUtil()
    {
    }

    public static JedisPool getJedisPoolInstance()
    {
        if(jedisPool==null)
        {
            synchronized (JedisPoolUtil.class)
            {
                if (jedisPool==null)
                {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(200);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100*1000);
                    poolConfig.setBlockWhenExhausted(true);
                    // 是否是一个连通状态
                    poolConfig.setTestOnBorrow(true);  //ping PONG

                    jedisPool=new JedisPool(poolConfig,"192.168.140.129",6379,60000);
                }
            }
        }
        return jedisPool;
    }
    public static void release(JedisPool jedisPool, Jedis jedis)
    {
        if(jedis!=null){}
          jedisPool.returnResource(jedis);
    }
}
