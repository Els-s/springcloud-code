package org.javaboy.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.springframework.web.client.RestTemplate;

public class HelloCommand extends HystrixCommand<String> {
    RestTemplate restTemplate;
    private Integer id;

    public HelloCommand(RestTemplate restTemplate,Integer id) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
//        int i = 1 / 0;
        System.out.println("run");
        return restTemplate.getForObject("http://provider/hello", String.class);
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    @Override
    protected String getFallback() {
        Throwable t = getExecutionException();
        return "error-2>"+t.getMessage();
    }
}
