package com.qunincey.security.core.properties;

import lombok.Data;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-25 20:08
 **/
@Data
public class QQProperties  {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;
    private String providerId = "qq";

}
