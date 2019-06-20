package com.qunincey.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-16 16:04
 **/
@Controller
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferreResultHolder deferreResultHolder;

    @GetMapping(value = "/order")
    public DeferredResult<String> order() throws InterruptedException {

        logger.info("主线程");
        String orderNumber = RandomStringUtils.random(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result= new DeferredResult<>();
        deferreResultHolder.getMap().put(orderNumber,result);

//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                Thread.sleep(1000);
//                logger.info("主线程返回");
//                return "success";
//            }
//        };

        return result;

    }
}
