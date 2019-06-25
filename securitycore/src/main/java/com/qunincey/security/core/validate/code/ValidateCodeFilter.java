package com.qunincey.security.core.validate.code;

import com.qunincey.security.core.properties.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.qunincey.security.core.properties.SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM;
import static com.qunincey.security.core.properties.SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-23 20:14
 **/
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    @Getter
    @Setter
    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @Getter
    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;


    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        urlMap.put(DEFAULT_SIGN_IN_PROCESSING_URL_FORM,ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(),ValidateCodeType.IMAGE);

        urlMap.put(DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(),ValidateCodeType.SMS);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeType validateCodeType=getValidateCodeType(httpServletRequest);

        if (validateCodeType!=null){
            logger.info("校验请求(" + httpServletRequest.getRequestURI() + ")中的验证码,验证码类型" + validateCodeType);

            ValidateCodeProcessor validateCodeProcessor=validateCodeProcessorHolder.findValidateCodeProcessor(validateCodeType);
            validateCodeProcessor.validate(new ServletWebRequest(httpServletRequest,httpServletResponse));

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);


    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }

    public void addUrlToMap(String urlString, ValidateCodeType type){
        if (StringUtils.isNotBlank(urlString)){
            String[] urls =StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString,",");
            for (String url:
                 urls) {
                urlMap.put(url,type);
            }
        }

    }
}
