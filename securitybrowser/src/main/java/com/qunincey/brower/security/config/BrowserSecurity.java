package com.qunincey.brower.security.config;

import com.qunincey.security.core.properties.SecurityProperties;
import com.qunincey.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        validateCodeFilter.
//
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authention/form")
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),
                        "/code/image").permitAll()
                .anyRequest()
                .authenticated()
        .and().csrf().disable();
    }
}
