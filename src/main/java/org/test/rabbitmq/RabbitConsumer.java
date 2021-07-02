package org.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;


public class RabbitConsumer {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    public void sendMessage(String message){
        String uri = System.getenv("RABBIT_URI");
        try {
            connectionFactory.setUri(uri);
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare("t_queue", false, false, false, null);
            channel.basicPublish("",
                    "t_queue",
                    null,
                    message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
