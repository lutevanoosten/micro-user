package com.luteflex.microservices.user.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;


public class Receiver {

    //private final static String QUEUE_NAME = "Returned_token";

    static Channel authservice;
    public static void main(String[] argv) throws Exception {



    }

    public static String gettoken(String QUEUE_NAME) throws IOException, InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        AtomicReference<String> m = new AtomicReference<>("");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

           m.set(new String(delivery.getBody(), "UTF-8"));

            System.out.println(" [x] Received '" + m + "'");

        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        while (m.get().isEmpty()){
            System.out.println("List is empty...");

            System.out.println("Waiting...");
        }
        return m.toString();

    }

}