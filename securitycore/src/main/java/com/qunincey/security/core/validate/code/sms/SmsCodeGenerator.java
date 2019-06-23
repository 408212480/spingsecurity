package com.qunincey.security.core.validate.code.sms;

import com.qunincey.security.core.properties.SecurityProperties;
import com.qunincey.security.core.validate.code.ValidateCode;
import com.qunincey.security.core.validate.code.ValidateCodeGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-22 17:30
 **/

public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Getter
    @Setter
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }


}
