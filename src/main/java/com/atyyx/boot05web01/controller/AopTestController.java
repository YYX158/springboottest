package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.bean.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopTestController {
    @Autowired
    Calculator calculator;

    @GetMapping("/add")
    public String add()
    {
        calculator.add(2,3);
        return "add";
    }
}
