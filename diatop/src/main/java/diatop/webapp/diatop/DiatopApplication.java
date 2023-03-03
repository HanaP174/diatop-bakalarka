package diatop.webapp.diatop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class DiatopApplication {
  @GetMapping("/resource")
  @CrossOrigin(origins = "*", maxAge = 3600,
          allowedHeaders={"X-Auth-Token", "x-requested-with", "x-xsrf-token"})
  public Map<String,Object> home() {
    Map<String,Object> model = new HashMap<>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello World");
    return model;
  }

  @Configuration
  @Order(SecurityProperties.DEFAULT_FILTER_ORDER)
  @EnableWebSecurity
  protected static class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
      return http.build();
    }
  }
  public static void main(String[] args) {
        SpringApplication.run(DiatopApplication.class, args);
    }

}
