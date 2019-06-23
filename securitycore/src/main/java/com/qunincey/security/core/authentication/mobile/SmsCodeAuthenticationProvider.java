package com.qunincey.security.core.authentication.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-23 16:05
 **/

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Getter
    @Setter
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (userDetails == null){
            throw new InternalAuthenticationServiceException("无法读取用户信息");
        }

        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails.getAuthorities(),userDetails);

        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
