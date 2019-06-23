package com.qunincey.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-23 16:25
 **/
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler qunAuthentionSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler qunAuthentionFailHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFiltter smsCodeAuthenticationFiltter =new SmsCodeAuthenticationFiltter();

        smsCodeAuthenticationFiltter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        smsCodeAuthenticationFiltter.setAuthenticationSuccessHandler(qunAuthentionSuccessHandler);
        smsCodeAuthenticationFiltter.setAuthenticationFailureHandler(qunAuthentionFailHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFiltter, UsernamePasswordAuthenticationFilter.class);
    }
}
