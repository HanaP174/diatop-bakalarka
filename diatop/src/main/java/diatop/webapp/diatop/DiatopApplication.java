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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
public class DiatopApplication {
  @GetMapping("/resource")
  public Map<String,Object> home() {
    Map<String,Object> model = new HashMap<>();
    model.put("id", UUID.randomUUID().toString());
    model.put("content", "Hello World");
    return model;
  }

  @RequestMapping("/ui/user")
  public Principal user(Principal user) {
    return user;
  }

//  @RequestMapping("/ui/token")
//  public Map<String,String> token(HttpSession session) {
//    return Collections.singletonMap("token", session.getId());
//  }

  @Configuration
  @Order(SecurityProperties.DEFAULT_FILTER_ORDER)
  @EnableWebSecurity
  protected static class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
        .httpBasic()
        .and()
        .authorizeRequests()
              .antMatchers("/index.html", "/", "/home", "/login", "/*.js", "/*.css").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf()
          .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
              .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
      return http.build();
    }
  }
  public static void main(String[] args) {
        SpringApplication.run(DiatopApplication.class, args);
    }

}
