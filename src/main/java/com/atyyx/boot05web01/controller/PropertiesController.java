package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.bean.BeanPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertiesController {

    // 如果拿不到就取默认值（冒号以后）
    @Value("${person.name:李四}")
    private String name;

    @Autowired
    BeanPerson beanPerson; // 由于直接在容器中了，所以可以直接自动注入

    @GetMapping("/look")
    public String hello()
    {
        return beanPerson.getClass().toString();
    }
}
