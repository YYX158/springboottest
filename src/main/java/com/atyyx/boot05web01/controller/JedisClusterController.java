package com.atyyx.boot05web01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

/**
 * 集群操作
 */
@RestController
public class JedisClusterController 
{
    
    @GetMapping("/getJedisCluster")
    public String getJedisCluster()
    {
        //Set<>
        // 创建对象
        HostAndPort hostAndPort = new HostAndPort("192.168.140.129", 6379);
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);

        //进行操作
         jedisCluster.set("b1", "value1");
        String value = jedisCluster.get("b1");
        System.out.println(value);

        //关闭jedis
        jedisCluster.close();
        return "";
    }
}
