package com.atyyx.boot05web01.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public interface BeanPerson
{
    public  String getName();
    public  Integer getAge();
}
