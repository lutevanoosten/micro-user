package com.luteflex.microservices.user.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;


public class Receiver {

    //private final static String QUEUE_NAME = "Returned_token";

    static Channel authservice;

    public static String gettoken(String QUEUE_NAME) throws IOException, InterruptedException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://user:RXnf11alr9eq@ec2-35-156-90-184.eu-central-1.compute.amazonaws.com:5672");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        AtomicReference<String> m = new AtomicReference<>("");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

           m.set(new String(delivery.getBody(), "UTF-8"));

            System.out.println(" [x] Received '" + m + "'");

        };
        m.set("Er wordt een update uitgevoerd, probeer het over enkele minuten opnieuw");
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        long startTime = System.currentTimeMillis();
        while (m.get().length() < 100){
            if ((System.currentTimeMillis()-startTime) > 10000){
                channel.close();
                break;
            }
        }

        channel.close();
        return m.toString();

    }

}