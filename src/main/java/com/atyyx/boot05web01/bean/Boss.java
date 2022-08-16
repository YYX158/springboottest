package com.atyyx.boot05web01.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("test")
@Component
@ConfigurationProperties("person")
@Data
public class Boss implements BeanPerson{

    private String name;
    private Integer age;


}

