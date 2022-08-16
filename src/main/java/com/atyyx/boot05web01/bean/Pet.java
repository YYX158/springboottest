package com.atyyx.boot05web01.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor // 指定一个无参构造函数
public class Pet {
    private String name;
    private Integer age;
}
