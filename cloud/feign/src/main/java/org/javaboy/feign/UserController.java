package org.javaboy.feign;

import org.javaboy.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/test1")
    public void test1() {
        User userById = userService.getUserById(99);
        System.out.println(userById);
    }
}
