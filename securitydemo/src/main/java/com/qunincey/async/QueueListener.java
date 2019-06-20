package com.qunincey.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-16 16:26
 **/

@Component
public class QueueListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferreResultHolder deferreResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());



    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {

        new Thread(()->{
            while (true){
                if (!StringUtils.isEmpty(mockQueue.getCompleteOrder())){
                    String orderNumber = mockQueue.getCompleteOrder();
                    logger.info("返回订单处理结果"+orderNumber);
                    deferreResultHolder.getMap().get(orderNumber).setResult("place order access");

                    mockQueue.setCompleteOrder(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();


    }
}
