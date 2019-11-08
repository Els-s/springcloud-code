package org.javaboy.consumer;

import org.javaboy.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class UseUserController {
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @GetMapping("/test1")
    public void test1() {
//        ResponseEntity<User> forEntity = restTemplate.getForEntity("http://provider/user/?id={1}", User.class, 99);
        Map<String, Object> map = new HashMap<>();
        map.put("id", 99);
        ResponseEntity<User> entity = restTemplate.getForEntity("http://provider/user/?id={id}", User.class, map);
        System.out.println("entity.getStatusCode():"+entity.getStatusCode());
        System.out.println("entity.getStatusCodeValue()"+entity.getStatusCodeValue());
        System.out.println("entity.getBody():"+entity.getBody());
        HttpHeaders headers = entity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String s : keySet) {
            System.out.println("key:"+s+";value:"+headers.get(s));
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        User user = new User();
        user.setId(99);
        user.setUsername("javaboy");
        user.setAddress("shenzhen");
        User u = restTemplate.postForObject("http://provider/user/", user, User.class);
        System.out.println(u);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        restTemplate.put("http://provider/user/", u);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        restTemplate.delete("http://provider/user/{1}", 99);
    }
}
