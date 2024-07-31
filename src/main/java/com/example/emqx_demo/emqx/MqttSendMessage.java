package com.example.emqx_demo.emqx;

import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @program: emqx_demo
 * @description: 发送消息
 * @author: shouhang.liu
 * @create: 2024-07-31 13:12
 **/
@Component
public interface MqttSendMessage {
    /**
     * 发送消息到MQTT服务器
     *
     * @param data 数据
     */
    void sendToMqtt(String data);

    /**
     * 发送消息到MQTT服务器
     *
     * @param topic   主题
     * @param payload 消息
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    /**
     * 发送消息到MQTT服务器
     *
     * @param topic   主题
     * @param qos     消息质量
     * @param payload 消息
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

    /**
     * 发送消息到MQTT服务器
     *
     * @param topic   主题
     * @param qos     消息质量
     * @param payload 消息
     */
    void sendToMqtt( String topic, boolean retained,int qos, String payload);

}
