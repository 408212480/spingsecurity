package com.qunincey.service.impl;

import com.qunincey.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-16 14:32
 **/

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println(name);
        return name;
    }
}
