package com.atyyx.boot05web01.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("prod")  //指定在prod环境下才生效
@Component
@ConfigurationProperties("person")
@Data
public class Worker  implements BeanPerson{

    private String name;
    private Integer age;


}
