package com.demo.rabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;
/**
 * @Description
 * @Author longjianyong
 * @Date 2019-06-04 11:47
 * @Version 1.0
 **/

/**
 * 异步confirm模式
 * <p>
 * 异步confirm模式是在生产者客户端添加ConfirmListener回调接口，重写接口的handAck()和handNack()方法，
 * 分别用来处理RabblitMQ回传的Basic.Ack和Basic.Nack。
 * <p>
 * 这两个方法都有两个参数，第1个参数deliveryTag用来标记消息的唯一序列号，
 * 第2个参数multiple表示的是是否为多条确认,值为true代表是多个确认，值为false代表是单个确认
 */

public class AsynConfirmRabbitProducer {
    private final static String EXCHANGE_NAME = "async-confirm-exchange";

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
        long msgCount = 1;
        SortedSet<Long> confirmSet = new TreeSet<Long>();
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("Ack,SeqNo：" + deliveryTag + ",multiple：" + multiple);
                if (multiple) {
                    confirmSet.headSet(deliveryTag - 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("Nack,SeqNo：" + deliveryTag + ",multiple：" + multiple);
                if (multiple) {
                    confirmSet.headSet(deliveryTag - 1).clear();
                } else {
                    confirmSet.remove(deliveryTag);
                }
                // 注意这里需要添加处理消息重发的场景
            }
        });
        // 演示发送100个消息
        while (msgCount <= batchCount) {
            long nextSeqNo = channel.getNextPublishSeqNo();
            channel.basicPublish(EXCHANGE_NAME, "", null, "async confirm test".getBytes());
            confirmSet.add(nextSeqNo);
            msgCount = nextSeqNo;
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
