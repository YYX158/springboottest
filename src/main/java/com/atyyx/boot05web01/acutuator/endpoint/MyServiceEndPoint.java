package com.atyyx.boot05web01.acutuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
@Endpoint(id = "myservice") // 声明自己的监控点的名称
public class MyServiceEndPoint {

    @ReadOperation // 必须声明是一个读操作
    public Map getDockerInfo()
    {
        return Collections.singletonMap("dockerInfo","docker started----------");
    }

    @WriteOperation // 声明是一个读操作
    public void stopDocker()
    {
        System.out.println("docker stopped ----------------");
    }
}
