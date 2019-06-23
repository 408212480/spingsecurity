package com.qunincey.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-23 10:22
 **/

public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    void create(ServletWebRequest request) throws Exception;

}
