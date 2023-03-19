package com.diatop.gateway.configuration;

import com.diatop.gateway.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;

@Configuration
@EnableWebFluxSecurity
@EnableSpringWebSession
@ComponentScan("com.diatop.model.user")
@EnableR2dbcRepositories(basePackages = "com.diatop.model.user")
public class SecurityConfiguration {

  @Autowired
  private UserDetailsService userDetailsService;

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
      .authenticationManager(authenticationManager())
      .csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
      .and().securityContextRepository(webSessionServerSecurityContextRepository());

    return http.build();
  }

  @Bean
  public WebSessionServerSecurityContextRepository webSessionServerSecurityContextRepository() {
    return new WebSessionServerSecurityContextRepository();
  }

  @Bean
  public ReactiveAuthenticationManager authenticationManager() {
    UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
      new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    authenticationManager.setPasswordEncoder(passwordEncoder());
    return authenticationManager;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
