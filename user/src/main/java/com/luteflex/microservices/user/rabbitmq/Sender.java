package com.luteflex.microservices.user.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luteflex.microservices.user.Models.TokenRequest;
import com.luteflex.microservices.user.UserConfiguration;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;


import java.nio.charset.StandardCharsets;

public class Sender {

    private final static String QUEUE_NAME = "Request_token";


    public static void main(String[] argv) throws Exception {
        UserConfiguration userConfiguration = new UserConfiguration();
        //userConfiguration.Requesttoken();
    }
    public void requestToken(TokenRequest tokenRequest)throws Exception{
        ObjectMapper Obj = new ObjectMapper();
        String message = Obj.writeValueAsString(tokenRequest);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.print(" [x] Sent '" + message + "'");
        }
    }
}

