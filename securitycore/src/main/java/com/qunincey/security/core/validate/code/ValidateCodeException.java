package com.qunincey.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-21 13:59
 **/

public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = 1167377453102513176L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
