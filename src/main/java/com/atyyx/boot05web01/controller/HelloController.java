package com.atyyx.boot05web01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {

    // 请求进来，先去找Controller，看看能不能处理，不能处理的话再去找静态资源；
    // 如果静态资源找不到，就会返回404
    @RequestMapping("/bug.png")
    public String hello(HttpSession httpSession)
    {
        return "aaaa";
    } //就可以直接操作session

    @GetMapping("/user")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getUser(){return "GET-张三";}

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String saveUser(){return "POST-张三";}

    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String putUser() { return "PUT-张三"; }

    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public String deleteUser(){return "DELETE-张三";}

}
