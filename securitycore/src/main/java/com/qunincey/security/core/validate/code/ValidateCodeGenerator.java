package com.qunincey.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-22 17:28
 **/

public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);
}
