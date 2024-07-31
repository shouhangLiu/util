package com.example.emqx_demo.emqx;

import org.springframework.stereotype.Component;

@Component
public interface MqttSubscribe {

    /**
     * 订阅主题
     * @param topic 主题
     */
    void subscribe(String topic);

    /**
     * 订阅主题
     * @param topic 主题
     * @param qos 消息质量
     */
    void subscribe(String topic,int qos);

}
