package com.atyyx.boot05web01.controller;

import com.alibaba.druid.support.http.WebStatFilter;
import com.atyyx.boot05web01.bean.Stu;
import com.atyyx.boot05web01.bean.User;
import com.atyyx.boot05web01.service.impl.StuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class IndexController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    StuServiceImpl stuService;


    /**
     * 来到登录页面
     * @return
     */
    @GetMapping(value ={"/","/login"})
    public String loginPage()
    {
        return "login";
    }

    //@ResponseBody
    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model)
    {

        if(!user.getPassword().isEmpty() && !user.getUserName().isEmpty())
        {
            session.setAttribute("loginUser",user);
            return "/hard";
        }
        else
        {
            model.addAttribute("msg","账号密码错误");
            return "/";
        }
        // 登录成功以后，跳转到main页面去


    }


    @GetMapping("/main.html")
    public String mainPage()
    {
        // 是否登录  可以用拦截器或者过滤器来处理
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/sql")
    public String queryFromDb()
    {
        Long along = jdbcTemplate.queryForObject("select count(*) from stu",Long.TYPE);
        return along.toString();
    }

    /**
     * WebStatFilter  用于采集web-jdbc关联监控的数据
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter()
    {
        WebStatFilter webStatFilter = new WebStatFilter();

        FilterRegistrationBean<WebStatFilter> webStatFilterFilterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);
        webStatFilterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        webStatFilterFilterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        return webStatFilterFilterRegistrationBean;
    }

    @ResponseBody
    @GetMapping("/stu")
    public Stu getById(@RequestParam("id") Long id)
    {
        // Controller 只需要去调用Service
        return stuService.getStuById(id);
    }

}
