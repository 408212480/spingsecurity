package com.qunincey.security.core.properties;

import lombok.Data;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-22 16:36
 **/
@Data
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;
    private String url="/authention/moblie";

}
