package com.qunincey.app.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-29 18:45
 **/
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
    }
}
