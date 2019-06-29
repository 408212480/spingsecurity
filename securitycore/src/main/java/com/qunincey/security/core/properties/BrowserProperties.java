package com.qunincey.security.core.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.qunincey.security.core.properties.LoginType;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-20 14:27
 **/
@Data
public class BrowserProperties {


    public String loginPage = "/login.html";

    private LoginType loginType= LoginType.JSON;

    private String signUpUrl = "/imooc-signUp.html";

    private SessionProperties session = new SessionProperties();

    /**
     * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
     */
    private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;

    /**
     * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
     */
    private String signOutUrl;

}
