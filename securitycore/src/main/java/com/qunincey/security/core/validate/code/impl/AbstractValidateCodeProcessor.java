package com.qunincey.security.core.validate.code.impl;

import com.qunincey.security.core.validate.code.ValidateCode;
import com.qunincey.security.core.validate.code.ValidateCodeException;
import com.qunincey.security.core.validate.code.ValidateCodeGenerator;
import com.qunincey.security.core.validate.code.ValidateCodeProcessor;
import com.sun.xml.internal.ws.api.policy.ValidationProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;


/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-23 10:25
 **/

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }

    private void save(ServletWebRequest request, C validateCode) {
         sessionStrategy.setAttribute(request,SESSION_KEY_PREFIX+getProcessorType(request).toUpperCase(),validateCode);
    }

    /**
    * @Description: 生成校验码
    * @Param:
    * @return: C
    */
    private C generate(ServletWebRequest request) {
//        String type = getValidateCodeType(request).toString().toLowerCase();
//        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
//        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
//        if (validateCodeGenerator == null) {
//            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
//        }
        System.out.println(request.getContextPath());
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type+"CodeGenerator");
        return (C) validateCodeGenerator.generate(request);
    }

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }

//    /**
//     * 保存校验码
//     *
//     * @param request
//     * @param validateCode
//     */
//    private void save(ServletWebRequest request, C validateCode) {
//        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
//        validateCodeRepository.save(request, code, getValidateCodeType(request));
//    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

//    /**
//     * 根据请求的url获取校验码的类型
//     *
//     * @param request
//     * @return
//     */
//    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
//        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
//        return ValidateCodeType.valueOf(type.toUpperCase());
//    }

//    @SuppressWarnings("unchecked")
//    @Override
//    public void validate(ServletWebRequest request) {
//
//        ValidateCodeType codeType = getValidateCodeType(request);
//
//        C codeInSession = (C) validateCodeRepository.get(request, codeType);
//
//        String codeInRequest;
//        try {
//            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
//                    codeType.getParamNameOnValidate());
//        } catch (ServletRequestBindingException e) {
//            throw new ValidateCodeException("获取验证码的值失败");
//        }
//
//        if (StringUtils.isBlank(codeInRequest)) {
//            throw new ValidateCodeException(codeType + "验证码的值不能为空");
//        }
//
//        if (codeInSession == null) {
//            throw new ValidateCodeException(codeType + "验证码不存在");
//        }
//
//        if (codeInSession.isExpried()) {
//            validateCodeRepository.remove(request, codeType);
//            throw new ValidateCodeException(codeType + "验证码已过期");
//        }
//
//        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
//            throw new ValidateCodeException(codeType + "验证码不匹配");
//        }
//
//        validateCodeRepository.remove(request, codeType);
//
//    }
}
