package org.javaboy.stream;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
public class MySink {

    @StreamListener(Sink.INPUT)
    public void receive(String msg) {
        System.out.println(msg);
    }
}
