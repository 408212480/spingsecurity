package com.qunincey.brower.security.config;

import com.qunincey.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.qunincey.security.core.authentication.mobile.SmsCodeFilter;
import com.qunincey.security.core.properties.SecurityConstants;
import com.qunincey.security.core.properties.SecurityProperties;
import com.qunincey.security.core.validate.code.ValidateCodeFilter;
import com.qunincey.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

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

    @Autowired
    private AuthenticationSuccessHandler qunAuthentionSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler qunAuthentionFailHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer qunSocialSecurityConfig;



    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(qunSocialSecurityConfig)
                .and()
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(qunAuthentionSuccessHandler)
                .failureHandler(qunAuthentionFailHandler)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,securityProperties.getBrowser().getLoginPage(),
                        "/code/*","/auth/*",securityProperties.getBrowser().getSignUpUrl()).permitAll()
                .anyRequest()
                .authenticated()
        .and().csrf().disable();
    }
}
