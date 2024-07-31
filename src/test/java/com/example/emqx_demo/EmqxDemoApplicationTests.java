package com.example.emqx_demo;

import com.example.emqx_demo.emqx.MqttSendMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmqxDemoApplicationTests {

    @Autowired
    private MqttSendMessage mqttSendMessage;

    @Test
    void pushlish() {
        for (int i = 0; i < 10; i++) {
            mqttSendMessage.sendToMqtt("test/name", "hello mqtt............" + i);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void contextLoads() {
    }

}
