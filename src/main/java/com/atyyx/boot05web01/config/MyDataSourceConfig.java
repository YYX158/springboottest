package com.atyyx.boot05web01.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

//@Configuration
public class MyDataSourceConfig {

    // 默认的自动配置是判断容器中没有才会配置ConditionalOnMissingBean(DataSource.class)
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
   public DataSource dataSource() throws SQLException {
       DruidDataSource druidDataSource = new DruidDataSource();

       //druidDataSource.setUrl();

        //加入监控功能
       druidDataSource.setFilters("stat,wall");
       return druidDataSource;
   }

    /**
     * 配置druid的监控页面
     * @return
     */
   @Bean
   public ServletRegistrationBean statViewServlet()
   {
       StatViewServlet statViewServlet = new StatViewServlet();
       ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<StatViewServlet>(statViewServlet);


       registrationBean.addInitParameter("loginUsername","admin");
       registrationBean.addInitParameter("loginPassword","123456");
       return registrationBean;
   }

   @Bean
   public FilterRegistrationBean webStatFilter()
   {
       WebStatFilter webStatFilter = new WebStatFilter();
       FilterRegistrationBean<WebStatFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);
       filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));// 设置拦截的路径
       filterFilterRegistrationBean.addInitParameter("exclusions","*.js,*.jpg,*.png,*.jif,*.ico,*.css,/druid/*");

       return filterFilterRegistrationBean;

   }
}
