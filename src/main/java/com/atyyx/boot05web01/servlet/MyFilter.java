package com.atyyx.boot05web01.servlet;

import javax.servlet.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebFilter(urlPatterns = {"/css/*","/images/*"})  // 注意：这里是单*，springboot是**
@Slf4j
public class MyFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        log.info("MyFilter初始化已完成");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("MyFilter正在工作");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("MyFilter已经销毁了");
    }
}
