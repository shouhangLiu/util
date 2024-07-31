package com.example.emqx_demo.emqx;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mqtt客户端
 */
@Slf4j
@Component
public class MqttCustomerClient {
    @Autowired
    private PushCallback pushCallback;

    private static MqttClient client;

    public static MqttClient getClient() {
        return client;
    }

    public static void setClient(MqttClient client) {
        MqttCustomerClient.client = client;
    }

    /**
     * 客户端连接
     *
     * @param host     ip+端口
     * @param clientID 客户端Id
     * @param username 用户名
     * @param password 密码
     * @param timeout  超时时间
     * @param keeplive 保留数
     */
    public void connect(String host, String clientID, String username, String password, int timeout, int keeplive) {
        MqttClient client;

        try {
            client = new MqttClient(host, clientID, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setUserName(username);
            options.setPassword(password.toCharArray());
            options.setConnectionTimeout(timeout);
            options.setKeepAliveInterval(keeplive);
            MqttCustomerClient.setClient(client);
            try {
                client.setCallback(pushCallback);
                client.connect(options);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
