package com.demo.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class GeneralRabbitProducer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 下面的代码可有可无，ConectionFactory类中默认设置的用户名和密码都是guest
        //factory.setUsername("guest");
        //factory.setPassword("guest");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 指定一个队列，不存在的话自动创建,就算没下面这行代码，也可以发送成功，我估计是
        // basiczPublish里面做了优化
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送消息
        String message = "Hello World";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}
