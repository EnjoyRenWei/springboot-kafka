package com.kafka.config;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @Author renwei
 * @Description 消息发送者
 * @Date
 **/
@Component
public class KafkaSender<T> {
    Logger logger = LoggerFactory.getLogger(KafkaSender.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(T obj) {
        String json = JSON.toJSONString(obj);
        logger.info("-0-------------message = " + json);
        // topic为test
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test", json);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("生产者：消息发送失败" + throwable.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                logger.info("生产者：消息发送成功" + stringObjectSendResult);
            }
        });
    }
}
