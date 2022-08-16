package com.atyyx.boot05web01.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration(proxyBeanMethods = true) //要保证是个单实例对象
public class MyRegistConfig {

    @Bean  // 注册一个Servelet
    public ServletRegistrationBean myServlet()
    {
        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean(myServlet,"/my","/my02");
    }

    //@Bean
    public FilterRegistrationBean myFilter()
    {
        MyFilter myFilter = new MyFilter();

        //return new FilterRegistrationBean(myFilter,myServlet()); // 与myServlet() 拦截的请求是一样的

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("my","my02","/css/*"));
        return filterRegistrationBean;
    }

    //@Bean
    public ServletListenerRegistrationBean myListener()
    {
        MyServeletContextlListener myServeletContextlListener = new MyServeletContextlListener();

        return new ServletListenerRegistrationBean(myServeletContextlListener);
    }

}
