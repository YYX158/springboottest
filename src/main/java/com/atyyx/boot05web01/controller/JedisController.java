package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.bean.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

@RestController
public class JedisController {
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    Jedis jedis;

    // 创建Jedis
    @GetMapping("/testJedis")
    public String testJedis()
    {
        //查看连接工厂
        Class<? extends RedisConnectionFactory> aClass = redisConnectionFactory.getClass();
        System.out.println("连接工厂是"+aClass.toString());
        return aClass.toString();
    }


    @GetMapping("/useJedisKey")
    public void useJedisKey()
    {
        jedis=new Jedis("192.168.140.129",6379);
        // 访问所有的keys   keys *
        Set<String> keys = jedis.keys("*");
        System.out.println("数据库有以下这些keys:");
        keys.stream().forEach(s -> System.out.println(s));
        // 设置值
        jedis.set("k2","new_key");
        // 返回数据库的长度
        Long dbSize = jedis.dbSize();
        System.out.println("数据库的大小是:"+dbSize);
        // 判断一个key是否存在    exists k2
        System.out.println("判断一下k2是否存在在redis中:"+jedis.exists("k2"));
        // 判断一个key的生命周期   ttl k1
        Long live=jedis.ttl("k1");
        if(live==-1)
           System.out.println("k1永远不会过期");
        else
            System.out.println("k1还有"+live+"s就要过期啦");
        // 获取一个值  get k2
        String k2 = jedis.get("k2");
        System.out.println("k2的值是"+k2);
        // 针对String的操作
        System.out.println();
        System.out.println("================================针对String的操作==============================");
        jedis.mset("k3","v3","k4","v4"); // 相当于就是mset k3 v3 k4 v4
        List<String> mgetList = jedis.mget("k1", "k2", "k3");
        System.out.println("String操作取出来的k1,k2,k3分别是：");
        mgetList.stream().forEach(s -> System.out.println(s));
        System.out.println("================================针对List的操作==============================");
        jedis.lpush("k8","1","2","3","4"); // lpush k8 1 2 3 4 5
        List<String> k8 = jedis.lrange("k8", 0, -1);// lrange k8 0 -1 // 默认取出k8中的所有
        System.out.println("新插入进去的k8中的值是:");
        k8.stream().forEach(s-> System.out.print(s+"    "));
        System.out.println();
        System.out.println("================================针对Set的操作==============================");
        jedis.sadd("k9","1","2","3","4","v1");
        Set<String> k9 = jedis.smembers("k9");
        k9.stream().forEach(s -> System.out.print(s+" "));
        System.out.println();
        System.out.println("================================针对Hash的操作==============================");
        jedis.hmset("user", Collections.singletonMap("username","zhangsan"));
        jedis.hmset("user",Collections.singletonMap("age","18"));
        Set<String> hkeys = jedis.hkeys("user");// 获取user的value中的所有key
        List<String> hvals = jedis.hvals("user");//获取user中的所有value
        System.out.println("打印user中的所有key");
        hkeys.stream().forEach(s -> System.out.print(s+"  "));
        System.out.println();
        System.out.println("打印user中的所有value");
        hvals.stream().forEach(s -> System.out.println(s+"   "));
        // 操作第二个stu
        Map stu=new HashMap();
        stu.put("id","OIE18031");
        stu.put("age","22");
        stu.put("gender","男"); //用中文会出现编码问题
        jedis.hmset("stu:OIE18031",stu);
        //获取key中的所有数据
        Map<String, String> stuMap = jedis.hgetAll("stu:OIE18031");
        System.out.println("打印stu:OIE18031的所有信息");
        stuMap.forEach((s1,s2)-> System.out.println(s1+"=>"+s2));
        System.out.println("================================针对Zset的操作==============================");
        jedis.zadd("language",100l,"java");
        jedis.zadd("language",300l,"c++");
        jedis.zadd("language",400l,"php");
        jedis.zadd("language",500l,"mysql");
        Set<String> language = jedis.zrange("language", 0, -1);
        language.stream().forEach(s -> System.out.print(s+"  "));
        System.out.println();
        Long zrank = jedis.zrank("language", "c++");
        System.out.println("java排在第"+zrank+"名");
        Long zrevrank1 = jedis.zrevrank("language", "c++");
        System.out.println("c++排在第"+zrevrank1+"名");

        // 关闭redis
        jedis.close();

    }


    /**
     * 要求：
     * 1.输入手机号，点击发送后随机生成6位数字密码，2分钟有效
     * 2.输入验证码，点击验证，返回成功或者失败
     * 3.每个手机号每天只能输入3次
     * @param code 用户输入的验证码
     * @return
     */
    @GetMapping("/newCode/{code}")
    public void mobileCode(@PathVariable("code") String code)
    {
        MobilePhone mobilePhone=new MobilePhone();
        String tel="15859107159";
        //去生成验证码
        mobilePhone.getCode(tel);
        mobilePhone.verifyCode(tel,code);

    }
}
