package com.qunincey.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-23 15:51
 **/

public class SmsCodeAuthenticationFiltter extends AbstractAuthenticationProcessingFilter {

    public static final String QUN_FROM_MOBLIE_KEY = "mobile";
    private String mobileParameter = "username";

    /*
    * 是不是只处理post请求
    * */
    private boolean postOnly = true;

    public SmsCodeAuthenticationFiltter() {
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String moblie = this.obtainMoblie(request);
            if (moblie == null) {
                moblie = "";
            }
            moblie = moblie.trim();

            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(moblie);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected String obtainMoblie(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setMoblieParameter(String moblieParameter) {
        Assert.hasText(moblieParameter, "Username parameter must not be empty or null");
        this.mobileParameter = moblieParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMoblieParameter() {
        return this.mobileParameter;
    }

}
