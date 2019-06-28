package com.qunincey.security.core.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-25 20:12
 **/
@Data
public class SocialProperties {

    @Getter
    @Setter
    private String filterProcessUrl = "/auth";

    private QQProperties qq = new QQProperties();
}
