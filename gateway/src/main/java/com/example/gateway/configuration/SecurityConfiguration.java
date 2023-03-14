package com.example.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;

@Configuration
@EnableWebFluxSecurity
@EnableSpringWebSession
public class SecurityConfiguration {

  @Bean
  public MapReactiveUserDetailsService userDetailsService() {
    UserDetails user = User.builder()
      .username("user")
      .password(passwordEncoder().encode("pass"))
      .roles("USER")
      .build();

    UserDetails admin = User.builder()
      .username("admin")
      .password(passwordEncoder().encode("pass"))
      .roles("ADMIN")
      .build();
    return new MapReactiveUserDetailsService(user, admin);
  }

  @Bean
  public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
    http
      .httpBasic()
      .and()
      .authorizeExchange()
      .pathMatchers("/index.html", "/", "/login", "/*.js", "/*.css", "/favicon.ico", "/*.map").permitAll()
      .anyExchange().authenticated()
      .and()
      .formLogin().disable()
      .csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
      .and().securityContextRepository(webSessionServerSecurityContextRepository());

    return http.build();
  }

  @Bean
  public WebSessionServerSecurityContextRepository webSessionServerSecurityContextRepository() {
    return new WebSessionServerSecurityContextRepository();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
