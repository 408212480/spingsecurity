package com.qunincey.Controller;

import com.qunincey.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-21 18:42
 **/
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleUserNotExistException(UserNotExistException une){
        Map<String,Object> result=new HashMap<>();
        result.put("id",une.getId());
        result.put("message",une.getMessage());
        return result;
    }
}
