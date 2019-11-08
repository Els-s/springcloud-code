package org.javaboy.feign;

import feign.hystrix.FallbackFactory;
import org.javaboy.commons.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceFallBackFactory  implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public User getUserById(Integer id) {
                User user = new User();
                user.setUsername("zhangsan");
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
        };
    }
}
