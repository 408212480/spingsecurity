package com.qunincey.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-20 14:26
 **/

@Data
@ConfigurationProperties(prefix = "qun.security")
public class SecurityProperties {

    public BrowserProperties browser =new BrowserProperties();

    public ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();


}
