package com.demo.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送方确认机制
 * <p>
 * 发送方确认机制是指生产者将信道设置成confirm（确认）模式，一旦信道进入confirm模式，所有在该信道上面发布的消息都会被指派一个唯一的ID（从1开始），
 * 一旦消息被投递到RabbitMQ服务器之后，RabbitMQ就会发送一个确认（Basic.Ack）给生产者（包含消息的唯一ID），这就使得生产者知晓消息已经正确到达了目的地了。
 * <p>
 * 如果RabbitMQ因为自身内部错误导致消息丢失，就会发送一条nack（Basic.Nack）命令，生产者应用程序同样可以在回调方法中处理该nack指令。
 * <p>
 * 如果消息和队列是可持久化的，那么确认消息会在消息写入磁盘之后发出。
 * <p>
 * 事务机制在一条消息发送之后会使发送端阻塞，以等待RabbitMQ的回应，之后才能继续发送下一条消息。
 * <p>
 * 相比之下，发送方确认机制最大的好处在于它是异步的，一旦发布一条消息。生产者应用程序就可以在等信道返回确认的同时继续发送下一条消息，
 * 当消息最终得到确认后，生产者应用程序便可以通过回调方法来处理该确认消息。
 * <p>
 * <p>
 * <p>
 * 1)事务机制和publisher confirm机制是互斥的，不能共存。
 * 2)事务机制和publisher confirm机制确保的是消息能够正确地发送至RabbitMQ，这里的“发送至RabbitMQ”的含义是指消息被正确地发往至RabbitMQ的交换器，
 * 如果此交换器没有匹配的队列，那么消息也会丢失。所以在使用这两种机制的时候要确保所涉及的交换器能够有匹配的队列。
 * <p>
 * <p>
 * 比如上面的NormalConfirmProducer类发送的消息，发送到了交换器normal-confirm-exchange，但是该交换器并没有绑定任何队列，从业务角度来讲，消息仍然是丢失了。
 * <p>
 * 普通confirm模式是每发送一条消息后就调用channel.waitForConfirms()方法，之后等待服务端的确认，这实际上是一种串行同步等待的方式。因此相比于事务机制，性能提升的并不多。
 */

public class ConfirmRabbitProducer {

    private final static String EXCHANGE_NAME = "normal-confirm-exchange"; //此交换器中没有绑定队列，所以消息就丢了
    private final static String QUEUE_NAME = "hello1";
    public final static String ROUTING_KEY = "test.direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        //sendSimpleMessage();
        sendMutilMessage();
    }

    private static void sendMutilMessage() throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 创建一个Exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //给exchange绑定一个队列
        // 表示声明了一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 建立一个绑定关系:
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        channel.confirmSelect();
        int loopTimes = 10;
        for (int i = 0; i < loopTimes; i++) {
            try {
                // 发送消息
                String message = "normal confirm test" + i;
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
                if (channel.waitForConfirms()) {
                    System.out.println("send message success");
                } else {
                    System.out.println("send message failed");
                    // do something else...
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }

    private static void sendSimpleMessage() throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("localhost");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 创建一个Exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        try {
            // 将信道设置成confirm模式。
            channel.confirmSelect();
            // 发送消息
            String message = "normal confirm test";
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            // 等待发送消息的确认消息，如果发送成功，则返回ture，如果发送失败，则返回false。
            if (channel.waitForConfirms()) {
                System.out.println("send message success");
            } else {
                System.out.println("send message failed");
                // do something else...
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
