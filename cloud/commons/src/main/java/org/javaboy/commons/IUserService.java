package org.javaboy.commons;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface IUserService {
    @GetMapping("/")
    User getUserById(@RequestParam("id") Integer id) ;

    @PostMapping("/")
    User addUser(@RequestBody User user) ;

    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable("id") Integer id);

    @PutMapping("/")
    User updateUser(@RequestBody User user);

    @GetMapping("/{ids}")//1,2,3
    List<User> getUserById(@PathVariable("ids") String ids);
}
