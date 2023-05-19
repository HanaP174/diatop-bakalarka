package com.diatop.diatop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
            .pathMatchers("/**", "/ui/**").hasRole("USER")
            .and()
            .csrf()
            .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse());
        return http.build();
    }
}
