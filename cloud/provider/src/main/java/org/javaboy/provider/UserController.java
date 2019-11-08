package org.javaboy.provider;

import org.javaboy.commons.IUserService;
import org.javaboy.commons.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController implements IUserService {
    @Override
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        System.out.println("getUserById>" + id);
        return user;
    }

    @Override
    public User addUser(@RequestBody User user) {
        return user;
    }

    @Override
    public void deleteUserById(@PathVariable Integer id) {
        System.out.println("deleteUserById>" + id);
    }

    @Override
    public User updateUser(@RequestBody User user) {
        System.out.println("updateUser>" + user);
        return user;
    }

    @Override
    public List<User> getUserById(@PathVariable String ids) {
        System.out.println("getUserById>" + ids);
        String[] split = ids.split(",");
        List<User> users = new ArrayList<>();
        for (String s : split) {
            User e = new User();
            e.setId(Integer.parseInt(s));
            users.add(e);
        }
        return users;
    }
}
