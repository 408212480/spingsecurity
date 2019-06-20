package com.qunincey.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-16 14:23
 **/

public class MyConstrainValidtor implements ConstraintValidator<MyConstrain,Object> {



    @Override
    public void initialize(MyConstrain constraintAnnotation) {
        System.out.println("initialize");
    }

    @Override
    public boolean isValid(Object t, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(t);
        return false;
    }
}
