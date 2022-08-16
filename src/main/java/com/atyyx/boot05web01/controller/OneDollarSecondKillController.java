package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.utils.JedisPoolUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;

@RestController
public class OneDollarSecondKillController {

    @GetMapping("/OneDollarSecondKill")
    public boolean OneDollarSecondKill() throws IOException
    {
        String uid="18296420466";
        String proid="ABC";
       //1.uid和prodid非空判断
        if(uid.equals(null) || proid.equals(null))
            return false;
        //2.连接redis
        //Jedis jedis=new  Jedis("192.168.140.129",6379);

        //2.通过连接池得到jedis对象
        //创建连接池对象
        JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
        // 通过连接超时，来解决连接超时的问题
        Jedis jedis = jedisPoolInstance.getResource();
        // 3.拼接key
        //3.1库存key
        String kcKey="sk:"+proid+":qt";
        //3.2 秒杀成功用户key
        String userKey ="sk:"+proid+":user";

        // 先对库存做一个监视
        jedis.watch(kcKey);
        //4.获取库存，如果库存库存是空的，那么秒杀还没有开始
        String kc = jedis.get(kcKey);
        if (kc==null)
        {
            System.out.println("秒杀还没开始，请耐心等待");
            // 关闭redis的连接
            jedis.close();
            return false;
        }

        //5.判断用户是否重复秒杀操作
        Boolean sismember = jedis.sismember(userKey, uid);
        if(sismember)  // 如果已经在我秒杀清单中存在了，那么就是重复操作
        {
            System.out.println("你已经秒杀过了，不能够重复操作");
            // 关闭redis的连接
            jedis.close();
            return false;
        }
        //6.判断如果商品数量的库存量小于1，秒杀就结束了
        if(Integer.parseInt(kc)<=0)
        {
            System.out.println("秒杀已经结束了");
            // 关闭redis的连接
            jedis.close();
            return false;
        }
        //7.秒杀的过程    --利用乐观锁解决超卖问题
        //需要添加事务
        Transaction multi = jedis.multi();
        // 先进行组队
        multi.decr(kcKey);// 7.1 库存要-1
        multi.sadd(userKey,uid); //7.2 把秒杀成功的用户添加到清单里面
        List<Object> result = multi.exec();//执行方法
        if(result==null || result.size()==0)
        {
            System.out.println("秒杀失败了");
            jedis.close();
            return false;
        }

        // 7.1 库存要-1
       // jedis.decr(kcKey);
        //7.2 把秒杀成功的用户添加到清单里面
        //jedis.sadd(userKey,uid);
        System.out.println("恭喜您，已经抢到特价商品了！");
        // 关闭redis的连接
        jedis.close();
        return true;
    }
}
