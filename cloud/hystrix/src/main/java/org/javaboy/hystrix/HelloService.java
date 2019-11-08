package org.javaboy.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    @CacheResult
    public String hello(Integer id) {
        System.out.println("hello");
        return restTemplate.getForObject("http://provider/hello", String.class);
    }

    public String error(Integer id,Throwable throwable) {
        return "error>"+ throwable.getMessage();
    }
}
