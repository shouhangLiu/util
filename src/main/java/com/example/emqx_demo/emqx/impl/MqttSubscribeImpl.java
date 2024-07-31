package com.example.emqx_demo.emqx.impl;

import com.example.emqx_demo.emqx.MqttCustomerClient;
import com.example.emqx_demo.emqx.MqttSubscribe;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

/**
 * @program: emqx_demo
 * @description:
 * @author: shouhang.liu
 * @create: 2024-07-31 13:39
 **/
@Service
@Slf4j
public class MqttSubscribeImpl implements MqttSubscribe {
    /**
     * 订阅某个主题，qos默认为0
     * @param topic 主题
     */
    public void subscribe(String topic){
        log.error("开始订阅主题" + topic);
        subscribe(topic,0);
    }

    /**
     * 订阅某个主题
     * @param topic 主题
     * @param qos 消息质量
     */
    public void subscribe(String topic,int qos) {
        try {
            MqttCustomerClient.getClient().subscribe(topic,qos);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

}
