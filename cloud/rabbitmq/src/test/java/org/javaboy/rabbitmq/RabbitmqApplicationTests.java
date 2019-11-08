package org.javaboy.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.core.MessagingTemplate;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    AmqpTemplate amqpTemplate;

    @Test
    void contextLoads() {
        amqpTemplate.convertAndSend("javaboy.org", "hello cloud bus");
    }

}
