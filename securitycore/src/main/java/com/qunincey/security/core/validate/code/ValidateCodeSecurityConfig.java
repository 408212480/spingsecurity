package com.qunincey.security.core.validate.code;

import com.qunincey.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-24 16:15
 **/
@Component
public class ValidateCodeSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private ValidateCodeFilter codeFilter;

    @Autowired
    private AuthenticationFailureHandler qunAuthentionFailHandler;

    @Autowired
    public SecurityProperties securityProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        codeFilter.setAuthenticationFailureHandler(qunAuthentionFailHandler);
        codeFilter.setSecurityProperties(securityProperties);
        codeFilter.afterPropertiesSet();

        http.addFilterBefore(codeFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
