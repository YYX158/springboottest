package com.atyyx.boot05web01.bean;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class MobilePhone {
    //private static String code;

    /**
     * 生成验证码
     * @return
     */
    public   void getCode(String tel)
    {
        String code="";
        // 连接redis
        Jedis jedis=new Jedis("192.168.140.129",6379);

        // 拼接上手机发送验证码的次数
        String coutKey="VerifyCode"+tel+":count";

        //拼接上手机验证码
        String codeKey="VerifyCode"+tel+":code";


        String cnt = jedis.get(coutKey);
        if(cnt==null)
        {

            // 创建二维码
            Random random = new Random();
            for (int i=0;i<6;i++)
            {
                code=code+random.nextInt(10);;
            }
            //将生成的验证码放入redis中  设置5分钟就过期
            jedis.setex(codeKey,5*60,code);
            jedis.close();// 关闭数据库连接
            System.out.println("验证码是"+code);

            //设置今天发动短信的次数
            jedis.setex(coutKey,60*60*24,"1");
        }
        else if (Integer.parseInt(cnt)>2)
        {
            System.out.println("当天已经用满3次了，请明天在试");
        }
        else
        {

            //生成验证码
            Random random = new Random();
            for (int i=0;i<6;i++)
            {
                code=code+random.nextInt(10);;
            }

            //将生成的验证码放入redis中  设置5分钟就过期
            jedis.setex(codeKey,5*60,code);
            jedis.close();// 关闭数据库连接
            System.out.println("验证码是"+code);
            //增加访问次数
            jedis.incr(coutKey);//增加访问次数
        }


    }

    /**
     * 2.每个手机每天只能发送三次，验证码放到redis中，设置过期时间
     * @param tel    手机号
     * @param code   验证码
     */
    public static void verifyCode(String tel,String code)
    {
        // 连接redis
        Jedis jedis=new Jedis("192.168.140.129",6379);
        //拼接上手机验证码
        String codeKey="VerifyCode"+tel+":code";
        Boolean codeExists = jedis.exists(codeKey);
        if(codeExists)
        {
            String code_right = jedis.get(codeKey);
            if(code_right.equals(code))
            {
                System.out.println("验证码输入正确");
            }
            else
            {
                System.out.println("验证码输入错误");
            }
        }
        else{
            System.out.println("验证码已经过期了，请重新获取");
        }

        // 关闭redis
        jedis.close();
    }
}
