package com.qunincey.security.core.properties;

import lombok.Data;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-22 16:36
 **/
@Data
public class ImageCodeProperties {

    private int width = 67;
    private int height = 67;
    private int length = 4;
    private int expireIn = 60;
    private String url ="/authention/form";
}
