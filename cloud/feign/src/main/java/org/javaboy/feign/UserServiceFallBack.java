package org.javaboy.feign;

import org.javaboy.commons.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-11-02 11:01
 */
//@Component
//@RequestMapping("/aaa")
public class UserServiceFallBack implements UserService {
    @Override
    public User getUserById(Integer id) {
        User user = new User();
        user.setUsername("javaboy");
        return user;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public void deleteUserById(Integer id) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public List<User> getUserById(String ids) {
        return null;
    }
}
