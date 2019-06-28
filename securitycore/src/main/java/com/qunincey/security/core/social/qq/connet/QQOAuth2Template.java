package com.qunincey.security.core.social.qq.connet;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-28 16:04
 **/

public class QQOAuth2Template extends OAuth2Template {

    private Logger logger= LoggerFactory.getLogger(QQOAuth2Template.class);

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String repoonstr = getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);
        logger.info("获取access token的响应"+repoonstr);

        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(repoonstr,"&");

        String accessToken = StringUtils.substringAfterLast(items[0],"=");
        Long expiresIn = Long.valueOf(StringUtils.substringAfterLast(items[1],"="));
        String refreshToken = StringUtils.substringAfterLast(items[2],"=");
        return new AccessGrant(accessToken,null,refreshToken,expiresIn);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate= super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return  restTemplate;
    }
}
