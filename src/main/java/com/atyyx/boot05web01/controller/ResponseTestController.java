package com.atyyx.boot05web01.controller;

import com.atyyx.boot05web01.bean.Person;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.FileSystem;
import java.util.Date;

@Controller
public class ResponseTestController {

    /**
     * 内容协商
     * 1. 浏览器发请求，直接返回xml      [application/xml]     jacksonXmlConverter
     * 2. 如果是ajax发请求，则返回json      [application/json]  jacksonJsonConverter
     * 3. 如果硅谷app发请求，返回自定义协议数据   [application/x-guigu]   xxxxConverter
     *
     *
     *
     *
     *
     * 步骤：
     * 1、 添加自定义的MessageConverter进系统底层
     * 2.  系统底层就会统计出所有MessageConverter能操作哪些类型
     * 3.  客户端内容协商  [guigu  ------> guigu]
     * @return
     */
    @ResponseBody
    //标注了@ResponseBody以后，SpringBoot会自动给我们封装成json
    @GetMapping("/test/person")
    public Person getPerson()
    {
        Person person = new Person();
        person.setAge(28);
        person.setBirth(new Date());
        person.setUserName("张三");
        return person;
    }

    @ResponseBody  // 会调用--RequestResponseBodyMethodProcessr---->messageConverter
    @GetMapping("/he11")
    public FileSystemResource file()
    {
        // 将文件以这样的方式返回，看是谁处理的  (messageConverter)
        return null;
    }

}
