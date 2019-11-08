package org.javaboy.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(MsgChannel.class)
public class MySink2 {
    @StreamListener(MsgChannel.INPUT)
    public void hello(String msg) {
        System.out.println("msg>>" + msg);
    }
}
