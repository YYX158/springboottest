package com.atyyx.boot05web01.config;

import com.atyyx.boot05web01.bean.Pet;
import com.atyyx.boot05web01.converter.GuiguMessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
public class WebConfig  implements WebMvcConfigurer// 由于jdk8已经给我默认实现了，就不需要每一个都实现
{
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter()
    {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");  //自己定义方法的名称
        return hiddenHttpMethodFilter;
    }
    
    //@Bean  // WebMvcConfigurer

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper(); // 创建一个路径帮助器
        urlPathHelper.setRemoveSemicolonContent(false);//不移除封号后面的内容，矩阵变量的功能才能生效

        configurer.setUrlPathHelper(urlPathHelper);
    }

    //第二种实现方式
    // 使用WebMvcConfiguer 定制化 SpringMVC的功能
    @Bean
    public WebMvcConfigurer webMvcConfigurer()
    {
        return new WebMvcConfigurer()
        {

            @Override
            /**
             * 自定义内容协商策略
             * @param configurer
             */
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
            {
                // 自己来定义内容协商策略
                //Map<String, MediaType> mediaTypes
                // 指定了三种参数的值
                Map<String, MediaType> mediaTypes=new HashMap<>();
                mediaTypes.put("json",MediaType.APPLICATION_JSON);
                mediaTypes.put("xml",MediaType.APPLICATION_XML);
                // 由于gg是我们自己定义的，所以需简要用参数的方式来获取值
                mediaTypes.put("gg",MediaType.parseMediaType("application/x-guigu"));
                //2.指定支持解析哪些参数对应的媒体类型
                ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
               // parameterStrategy.setParameterName("ff");   // 指定内容协商参数的名字
                //创建基于头的解析参数
                HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();

                //添加适配器情况
                configurer.strategies(Arrays.asList(parameterStrategy,headerContentNegotiationStrategy));
            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new GuiguMessageConverter());
            }

            @Override
            public void configurePathMatch(PathMatchConfigurer configurer)
            {
                UrlPathHelper urlPathHelper = new UrlPathHelper(); // 创建一个路径帮助器
                urlPathHelper.setRemoveSemicolonContent(false);//不移除封号后面的内容，矩阵变量的功能才能生效

                configurer.setUrlPathHelper(urlPathHelper);
            }

            @Override
            // 自己去定义适配器
            public void addFormatters(FormatterRegistry registry)
            {
                registry.addConverter(new Converter<String, Pet>()
                {
                    @Override
                    public Pet convert(String source)
                    {
                        // 阿猫,3
                        if(!source.isEmpty())
                        {
                            Pet pet =new Pet();
                            String [] spilt=source.split(",");
                            pet.setName(spilt[0]);
                            pet.setAge(Integer.parseInt(spilt[1]));
                            return pet;
                        }
                        return null;
                    }
                });
            }
        };
    }
}
