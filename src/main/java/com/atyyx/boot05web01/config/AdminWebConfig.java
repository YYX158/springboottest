package com.atyyx.boot05web01.config;

import com.atyyx.boot05web01.interceptor.LoginInterceptor;
import com.atyyx.boot05web01.interceptor.ResidUrlInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    /**
     * 由于这些注释只有在容器中，SpringBoot才会去解析，所以不能自己new，要从容器中去拿。
     * Filter、Interceptor 几乎都拥有相同的功能
     * Filter是Servlet定义的组件，好处就是脱离了Spring的应用也能做
     * Interceptor是Spring定义的接口。可以使用Spring的自动装配等功能
     */
    @Autowired
    ResidUrlInterceptor residUrlInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {

        // 添加拦截器
        registry.addInterceptor(new LoginInterceptor())
        .addPathPatterns("/**")  // 拦截住所有的请求  // 静态资源也被拦截了
        .excludePathPatterns("/","/login","/css/**","/js/**","/images/**","/fonts/**","/error/**");
        //由于这些注释只有在容器中，SpringBoot才会去解析，所以不能自己new，要从容器中去拿。
        //registry.addInterceptor(residUrlInterceptor).addPathPatterns("/**")
               // .excludePathPatterns("/","/login","/css/**","/js/**","/images/**","/fonts/**","/error/**");
    }
}
