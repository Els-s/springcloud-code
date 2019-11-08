package org.javaboy.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.javaboy.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class UseHelloController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
//        String hello = helloService.hello(99);
//        hello = helloService.hello(99);
//        ctx.close();
//        return hello;
        HelloCommand cmd = new HelloCommand(restTemplate,99);
        HelloCommand cmd2 = new HelloCommand(restTemplate,99);
        String execute = cmd.execute();
        String execute2 = cmd2.execute();
        return execute;
    }

    @Autowired
    UserService userService;
    @GetMapping("/test1")
    public void test1() throws ExecutionException, InterruptedException {
        HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
        UserBatchCommand cmd1 = new UserBatchCommand(99, userService);
        UserBatchCommand cmd2 = new UserBatchCommand(98, userService);
        UserBatchCommand cmd3 = new UserBatchCommand(97, userService);
        Future<User> q1 = cmd1.queue();
        Future<User> q2 = cmd2.queue();
        Future<User> q3 = cmd3.queue();
        User user1 = q1.get();
        User user2 = q2.get();
        User user3 = q3.get();
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        Thread.sleep(2000);
        UserBatchCommand cmd4 = new UserBatchCommand(96, userService);
        Future<User> q4 = cmd4.queue();
        User user4 = q4.get();
        System.out.println(user4);
        ctx.close();
    }
}