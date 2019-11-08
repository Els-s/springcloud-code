package org.javaboy.feign;

import org.javaboy.commons.IUserService;
import org.javaboy.commons.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "provider",fallbackFactory = UserServiceFallBackFactory.class)
public interface UserService extends IUserService {
}
