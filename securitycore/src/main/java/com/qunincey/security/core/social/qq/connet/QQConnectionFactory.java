package com.qunincey.security.core.social.qq.connet;

import com.qunincey.security.core.social.qq.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-25 19:26
 **/

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {


    public QQConnectionFactory(String providerId,String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
