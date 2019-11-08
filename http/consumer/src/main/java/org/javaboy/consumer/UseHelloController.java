package org.javaboy.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class UseHelloController {
    @GetMapping("/hello")
    public void hello() throws IOException {
        HttpURLConnection con = null;
        URL url = new URL("http://localhost:8080/hello");
        con = (HttpURLConnection) url.openConnection();
        con.connect();
        if (con.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String s = br.readLine();
            System.out.println(s);
            br.close();
        }
    }
}
