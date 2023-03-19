package com.diatop.diatop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class DiatopApplication {
  @RequestMapping("/resource")
//  @CrossOrigin(origins = "*", maxAge = 3600,
//          allowedHeaders={"X-Auth-Token", "x-requested-with", "x-xsrf-token"})
  public Map<String,Object> home() {
    Map<String,Object> model = new HashMap<>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello World");
    return model;
  }

  public static void main(String[] args) {
        SpringApplication.run(DiatopApplication.class, args);
    }

}
