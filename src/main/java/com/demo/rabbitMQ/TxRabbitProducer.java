package com.demo.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 事务机制
 * RabblitMQ客户端中与事务机制相关的方法有以下3个：
 * <p>
 * channel.txSelect：用于将当前的信道设置成事务模式
 * channel.txCommit：用于提交事务
 * channel.txRollback：用于回滚事务
 * <p>
 * 虽然事务能够解决消息发送方和RabbitMQ之间消息确认的问题，只有消息成功被RabbitMQ接收，
 * 事务才能提交成功，否则便可在捕获异常之后进行事务回滚。但是使用事务机制会“吸干”RabbitMQ的性能，
 * 因此建议使用下面讲到的发送方确认机制。
 */
public class TxRabbitProducer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //sendSimpleMessageByTX();
        sendMutlMessageByTX();
    }

    // 发送多条
    private static void sendMutlMessageByTX() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 指定一个队列，不存在的话自动创建,就算没下面这行代码，也可以发送成功，我估计是
        // basiczPublish里面做了优化
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.txSelect();
        int loopTimes = 10;
        for (int i = 0; i < loopTimes; i++) {
            try {
                // 发送消息
                String message = "Hello World!" + i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                channel.txCommit();
                System.out.println(" [x] Sent '" + message + "'");
            } catch (Exception e) {
                e.printStackTrace();
                channel.txRollback();
            }
        }
    }

    private static void sendSimpleMessageByTX() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 指定一个队列，不存在的话自动创建,就算没下面这行代码，也可以发送成功，我估计是
        // basiczPublish里面做了优化
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        try {
            // 事务开始
            channel.txSelect();
            // 发送消息
            String message = "Hello World";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            //int result = 1 / 0;
            // 事务提交
            channel.txCommit();
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
            System.out.println("begin rollback");
            channel.txRollback();
            System.out.println("end rollback");
        }
        // 关闭通道和连接
        channel.close();
        connection.close();

    }
}
