package com.atyyx.boot05web01.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查
 * 1.配置好拦截器需要拦截哪些请求
 * 2.把这些配置放在容器中
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor
{
    /**
     * 目标方法执行以前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 登录检查逻辑
        HttpSession  session=request.getSession(); //获取session

        log.info("拦截的请求路径是"+request.getRequestURI());
        Object loginUser = session.getAttribute("loginUser");

        if(loginUser!=null)
        {
            // 放行
            return true;
        }
        else
        {
            // 如果没有登录，直接重定向到登录页面
            // 将请求转发到登录页面去
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/").forward(request,response); //转发
            return false;
        }

    }

    /**
     * 目标方法执行以后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 页面渲染以后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
