package com.atyyx.boot05web01.acutuator.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AppInfoInfoContributor implements InfoContributor
{
    @Override
    public void contribute(Info.Builder builder)
    {
        //Collections是工具类
        // .singletonMap() 可以快速创建一个map
        builder.withDetail("msg","你好").withDetail("hello","atyyx").withDetails(Collections.singletonMap("world","666666"));
    }
}
