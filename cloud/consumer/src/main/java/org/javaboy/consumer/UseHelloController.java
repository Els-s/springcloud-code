package org.javaboy.consumer;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
public class UseHelloController {
    @Autowired
    DiscoveryClient discoveryClient;
    Integer count = 0;

    @GetMapping("/hello")
    public void hello() throws IOException {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");//根据服务名获取服务的详细信息,因为provider 可能是集群
        ServiceInstance serviceInstance = list.get((count++) % list.size());
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hello";
        HttpURLConnection con = null;
        URL u = new URL(url);
        con = (HttpURLConnection) u.openConnection();
        con.connect();
        if (con.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String s = br.readLine();
            System.out.println(s);
            br.close();
        }
    }

    @Autowired
    @Qualifier("restTemplateOne")
    RestTemplate restTemplateOne;
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @GetMapping("/hello2")
    public void hello2() throws IOException {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");//根据服务名获取服务的详细信息,因为provider 可能是集群
        ServiceInstance serviceInstance = list.get((count++) % list.size());
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/hello";
        String s = restTemplateOne.getForObject(url, String.class);
        System.out.println(s);
    }

    @GetMapping("/hello3")
    public void hello3() throws IOException {
        String s = restTemplate.getForObject("http://provider/hello", String.class);
        System.out.println(s);
    }
}
