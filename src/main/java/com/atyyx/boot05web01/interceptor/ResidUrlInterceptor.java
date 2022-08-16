package com.atyyx.boot05web01.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ResidUrlInterceptor implements HandlerInterceptor {
    @Autowired
    StringRedisTemplate redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的uri
        String uri = request.getRequestURI();
        // 默认每次访问当前的uri就会计数+1
        redisTemplate.opsForValue().increment(uri);
        String s = redisTemplate.opsForValue().get(uri);
        System.out.println(uri+"访问了"+s+"次");
        return false;
    }
}
