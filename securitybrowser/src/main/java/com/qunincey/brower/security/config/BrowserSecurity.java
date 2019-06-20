package com.qunincey.brower.security.config;

import com.qunincey.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-20 13:05
 **/
@Configuration
public class BrowserSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    public SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authention/form")
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",securityProperties.getBrowserProperties().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
        .and().csrf().disable();
    }
}
