package com.example.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class ResourceApplication {
  @CrossOrigin(origins = "*", maxAge = 3600,
          allowedHeaders={"X-Auth-Token", "x-requested-with", "x-xsrf-token"})
  @RequestMapping("/test")
  public Message home() {
    return new Message("Hello World");
  }

  @Configuration
  @EnableWebSecurity
  protected static class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
              .cors()
              .and()
              .authorizeHttpRequests()
              .anyRequest()
              .authenticated()
              .and()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.NEVER);
      return http.build();
    }
  }

  @Bean
  public HeaderHttpSessionIdResolver httpSessionIdResolver() {
    return HeaderHttpSessionIdResolver.xAuthToken();
  }

  public static void main(String[] args) {
    SpringApplication.run(ResourceApplication.class, args);
  }

  class Message {
    private String id = UUID.randomUUID().toString();
    private String content;

    public Message(String content) {
      this.content = content;
    }

    public Message(String id, String content) {
      this.id = id;
      this.content = content;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }
  }
}
