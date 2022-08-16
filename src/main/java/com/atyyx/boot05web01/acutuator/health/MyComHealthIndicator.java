package com.atyyx.boot05web01.acutuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 要记得后缀必须要叫健康检查 HealthIndicator
 */
@Component // 健康检查组件
public class MyComHealthIndicator extends AbstractHealthIndicator
{
    /**
     * 真实的检查方法
     * @param builder
     * @throws Exception
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception
    {
        // mongodb   获取连接进行测试
        Map<String,Object> map=new HashMap<>();
        // 检查完成
        if(1==1)
        {
            //builder.up(); // 这是健康的
            builder.status(Status.UP);// 定义健康
            map.put("ms",100);
        }
        else
        {
            builder.status(Status.OUT_OF_SERVICE); //不健康
            map.put("err","连接超时");
            map.put("ms",3000);
        }
        builder.withDetail("code",1000).withDetails(map);

    }
}
