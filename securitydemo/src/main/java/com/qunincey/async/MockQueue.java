package com.qunincey.async;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-16 16:16
 **/
@Data
@Component
public class MockQueue {

    private String placeOrder;

    private String completeOrder;



}
