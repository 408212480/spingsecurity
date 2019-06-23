package com.qunincey.security.core.properties;

import lombok.Data;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-22 16:38
 **/
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image=new ImageCodeProperties();

    private SmsCodeProperties sms=new ImageCodeProperties();
}
