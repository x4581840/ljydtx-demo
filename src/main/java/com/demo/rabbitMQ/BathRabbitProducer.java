package com.demo.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019-06-04 11:45
 * @Version 1.0
 **/

/**
 * 批量confirm模式是每发送一批消息后，调用channel.waitForConfirms()方法，等待服务器的确认返回，因此相比于5.1中的普通confirm模式，性能更好。
 * <p>
 * 但是不好的地方在于，如果出现返回Basic.Nack或者超时情况，生产者客户端需要将这一批次的消息全部重发，
 * 这样会带来明显的重复消息数量，如果消息经常丢失，批量confirm模式的性能应该是不升反降的。
 */
public class BathRabbitProducer {

    private final static String EXCHANGE_NAME = "batch-confirm-exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
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
        int batchCount = 100;
        int msgCount = 0;
        BlockingQueue blockingQueue = new ArrayBlockingQueue(100);
        try {
            channel.confirmSelect();
            while (msgCount <= batchCount) {
                String message = "batch confirm test";
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
                // 将发送出去的消息存入缓存中，缓存可以是一个ArrayList或者BlockingQueue之类的
                blockingQueue.add(message);
                if (++msgCount >= batchCount) {
                    try {
                        if (channel.waitForConfirms()) {
                            // 将缓存中的消息清空
                            blockingQueue.clear();
                        } else {
                            // 将缓存中的消息重新发送
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 将缓存中的消息重新发送
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
