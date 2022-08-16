package com.atyyx.boot05web01;


import com.atyyx.boot05web01.bean.Calculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.Mapping;

import java.util.Map;

//@EnableCaching //表示启用缓存
@ServletComponentScan  //(basePackages = "con.atyyx.boot05web01") // 指明在哪个包下面有
@SpringBootApplication
public class Boot05Web01Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Boot05Web01Application.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        Map<String, Object> systemEnvironment = environment.getSystemEnvironment();
        Map<String, Object> systemProperties = environment.getSystemProperties();
        System.out.println("======================系统的环境变量======================");
        System.out.println(systemEnvironment);
        System.out.println("============================系统的属性===============");
        System.out.println(systemProperties);

//        Calculator bean=run.getBean(Calculator.class);
//        System.out.println(bean.add(2,3));
    }

}
