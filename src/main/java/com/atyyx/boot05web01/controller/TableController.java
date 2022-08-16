package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.bean.User;
import com.atyyx.boot05web01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class TableController
{

    @Autowired
    UserService userService;

    @GetMapping("/basic_table")
    public String basic_table()
    {

        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model)
    {
        List<User> userList = userService.list();//获取所有的信息

        model.addAttribute("users",userList);

        return "/table/dynamic_table";
    }

    @GetMapping("/editable_table")
    public String editable_table()
    {
        return "/table/editable_table";
    }

    @GetMapping("/pricing_table")
    public String pricing_table()
    {
        return "/table/pricing_table";
    }

    @GetMapping("/responsive_table")
    public String  responsive_table()
    {
        return "/table/responsive_table";
    }
}
