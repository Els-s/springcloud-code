package org.javaboy.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    @RabbitListener(queues = "javaboy.org")
    public void receive(String msg) {
        System.out.println(msg);
    }
}