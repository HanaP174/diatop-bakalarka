package com.diatop.admin.configuration;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/index.html", "/",  "/*.js", "/*.css", "/favicon.ico", "/*.map").permitAll()
      .antMatchers("/admin/**").hasRole("ADMIN")
      .anyRequest().authenticated()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
      .and()
      .csrf()
      .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    return http.build();
  }
}
