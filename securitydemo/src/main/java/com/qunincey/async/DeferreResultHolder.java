package com.qunincey.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-16 16:19
 **/
@Component
@Data
public class DeferreResultHolder {

    private Map<String, DeferredResult<String>> map= new HashMap<>();


}
