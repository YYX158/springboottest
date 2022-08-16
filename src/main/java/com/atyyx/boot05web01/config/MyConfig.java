package com.atyyx.boot05web01.config;

import com.atyyx.boot05web01.bean.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MyConfig {

    @Bean
    @Profile("prod")
    public Color red()
    {
        return new Color();
    }

    @Bean
    @Profile("test")
    public Color green()
    {
        return new Color();
    }
}
