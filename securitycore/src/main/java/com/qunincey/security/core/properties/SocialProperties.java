package com.qunincey.security.core.properties;

import lombok.Data;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-25 20:12
 **/
@Data
public class SocialProperties {

    private String appId;

    private String appSecret;

    private QQProperties qq = new QQProperties();
}
