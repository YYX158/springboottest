package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.bean.User;
import com.atyyx.boot05web01.config.MyBatisConfig;
import com.atyyx.boot05web01.service.UserService;
import com.atyyx.boot05web01.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
public class UserController
{
    /**
     * 需要注意的是，这里需要自动注入的是实体类对应的
     */
    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping("/getUserByID")
    public User getUserById(Long id)
    {
        User user = userService.getUserById(id);
        log.info("展示user={}",user.toString());
        return user;
    }

    //@ResponseBody

    /**
     *
     * @param pn   页面分页参数
     * @param model
     * @return
     */
    @GetMapping("/getAllUser")
    public String getAllUser(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model)
    {

        //List<User> userList = userService.list();//获取所有的信息

        //model.addAttribute("users",userList);

        // mybatis-plus要想开启分页设置，必须自己添加配置来开启
        // 已经将配置放在   com.atyyx.boot05web01.config.MyBatisConfig

        // 创建一个分页对象
        Page<User> userPage = new Page<>(pn, 2); // 第一个参数是当前页面  第二个参数是每一页展示几个变量
        // 分页查询
        Page<User> page = userService.page(userPage, null);// 第一个参数是分页变量，第二个参数是查询的参数
        // 获取所有的记录数
        List<User> records = page.getRecords();

        model.addAttribute("page",page);
        return "test";
    }
}
