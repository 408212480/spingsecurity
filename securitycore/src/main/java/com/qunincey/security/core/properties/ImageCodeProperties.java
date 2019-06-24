package com.qunincey.security.core.properties;

import lombok.Data;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-22 16:36
 **/
@Data
public class ImageCodeProperties extends SmsCodeProperties{

    private int width = 67;
    private int height = 67;
    private String url="/authentication/form" ;

    public ImageCodeProperties() {
        setLength(4);
    }
}
