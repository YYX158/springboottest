package com.atyyx.boot05web01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewTestController {

    @GetMapping("/atguigu")
    public String  atguigu(Model model)
    {
        //thymeleaf默认会去resource/templates/**.html下找
        //会自动完成字符串拼接的功能
        //model中的数据会自动放到请求域中
        //request.setAttribute("a",aa)
        model.addAttribute("msg","欢迎你,yyx");
        model.addAttribute("link","http://www.baidu.com");
        return "success";
    }
}
