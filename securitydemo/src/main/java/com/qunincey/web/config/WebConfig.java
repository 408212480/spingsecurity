package com.qunincey.web.config;

import com.qunincey.web.filter.TimeFilter;
import com.qunincey.web.interceptor.TimeInrerceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-21 19:05
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    public TimeInrerceptor timeInrerceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInrerceptor).excludePathPatterns("/login.html");
    }

    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);

        List<String> url = new ArrayList<>();
        url.add("/*");
        filterRegistrationBean.setUrlPatterns(url);

        return filterRegistrationBean;
    }
}
