package com.qunincey.security.core.social.qq.connet;

import com.qunincey.security.core.social.qq.api.QQ;
import com.qunincey.security.core.social.qq.api.QQImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-25 18:54
 **/

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    @Getter
    @Setter
    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }


    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
