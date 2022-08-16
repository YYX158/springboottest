package com.atyyx.boot05web01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request)
    {
        request.setAttribute("msg","成功了");
        request.setAttribute("code",200);
        return "forward:/success"; //转发到 /success页面当中
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute(value = "msg",required = false) String msg,
                       @RequestAttribute(value = "code",required = false) Integer code,
                       HttpServletRequest request //由于是转发过来，所以他们用的是同一个http请求
    )
    {

        Object msg1 = request.getAttribute("msg");

        Map<String,Object> map =new HashMap<>();
        map.put("reqMethod",msg1);
        //map.put("用注解取到的",msg);

        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object message = request.getAttribute("message");

        map.put("hello",hello);
        map.put("world",world);
        map.put("message",message);
        return map;
    }

    @GetMapping("/test")
   //@GetMapping("/goto")
    public String testParam(Map<String,Object>map,  //map跟model都是放在请求域中的
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response)
    {

        map.put("hello","world666");
        model.addAttribute("world","hello6666");
        request.setAttribute("message","Hello World");
        Cookie cookie = new Cookie("c1","v1");
        cookie.setDomain("localhost");// 设置作用域
        response.addCookie(cookie);
        return "forward:/success"; //转发到 /success页面当中
    }
}
