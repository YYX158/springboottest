package com.atyyx.boot05web01.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor //指定一个所有参数的构造函数
@NoArgsConstructor
public class Person {
    private String userName;
    private Integer age;
    private Date birth;
    private Pet pet;
}
