package org.javaboy.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    MsgChannel msgChannel;
    @GetMapping("/hello")
    public void hello() {
        msgChannel.output().send(MessageBuilder.withPayload("hello javaboy").build());
    }
}