package com.example.emqx_demo.emqx.impl;

import com.example.emqx_demo.emqx.MqttConfiguration;
import com.example.emqx_demo.emqx.MqttCustomerClient;
import com.example.emqx_demo.emqx.MqttSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: emqx_demo
 * @description:
 * @author: shouhang.liu
 * @create: 2024-07-31 13:15
 **/
@Service
@Slf4j
public class MqttSendMessageImpl implements MqttSendMessage {

    @Autowired
    private MqttConfiguration mqttConfiguration;

    @Override
    public void sendToMqtt(String data) {
        pushMessage(mqttConfiguration.getQos(), mqttConfiguration.getRetained(), mqttConfiguration.getTopic(), data);
    }

    @Override
    public void sendToMqtt(String topic, String payload) {
        pushMessage(mqttConfiguration.getQos(), mqttConfiguration.getRetained(), topic, payload);
    }

    @Override
    public void sendToMqtt(String topic, int qos, String payload) {
        pushMessage(qos, mqttConfiguration.getRetained(), topic, payload);
    }

    @Override
    public void sendToMqtt(String topic, boolean retained, int qos, String payload) {
        pushMessage(qos, retained, topic, payload);
    }

    /**
     * 发布
     *
     * @param qos         连接方式
     * @param retained    是否保留
     * @param topic       主题
     * @param pushMessage 消息体
     */
    public void pushMessage(int qos, boolean retained, String topic, String pushMessage) {
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mqttTopic = MqttCustomerClient.getClient().getTopic(topic);
        if (null == mqttTopic) {
            log.error("topic not exist");
        }
        MqttDeliveryToken token;
        try {
            token = mqttTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
