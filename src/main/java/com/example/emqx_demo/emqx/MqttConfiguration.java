package com.example.emqx_demo.emqx;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: emqx_demo
 * @description:
 * @author: shouhang.liu
 * @create: 2024-07-31 11:02
 **/
@Component
@Configuration
@Data
@ConfigurationProperties("mqtt")
public class MqttConfiguration {
    @Autowired
    private MqttCustomerClient mqttCustomerClient;

    @Autowired
    private MqttSubscribe mqttSubscribe;

    private String host;
    private String clientId;
    private String username;
    private String password;
    private String topic;
    private int qos;
    private Boolean retained;
    private int timeout;
    private int keepAlive;

    @Bean
    public MqttCustomerClient getMqttCustomerClient() {
        mqttCustomerClient.connect(host, clientId, username, password, timeout, keepAlive);
        // 以/#结尾表示订阅所有以test开头的主题
        mqttSubscribe.subscribe(topic);
        return mqttCustomerClient;
    }
}

