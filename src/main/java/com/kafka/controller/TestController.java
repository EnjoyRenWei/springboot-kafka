package com.kafka.controller;

import com.kafka.config.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author renwei
 * @Description 测试接口
 * @Date
 **/
@RestController
public class TestController {
    @Autowired
    private KafkaSender<Object> kafkaSender;

    /**
     * @Description //TODO
     *
     * @Date
     * @Param []
     * @return void
     **/
    @GetMapping(value = "/test")
    public void test() {
        Map<String, String> map = new HashMap<>(16);
        map.put("1234", "kj");
        map.put("332", "234");
        kafkaSender.send(map);
    }
}
