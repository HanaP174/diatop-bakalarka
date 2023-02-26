package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class GatewayApplication {

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//
//		return builder.routes()
////				.route(r -> r.path("/diatop/**").and().uri("http://localhost:4200"))
//				.route(r -> r.path("/resource/**").uri("http://localhost:9000"))
//				.route(r -> r.path("/ui/**").uri("http://localhost:8081"))
//				.build();
//	}

	@Configuration
	@EnableWebSecurity
	protected static class SecurityConfiguration {
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
//				.authorizeRequests()
//				.antMatchers("/index.html", "/", "/home", "/login", "/*.js", "/*.css").permitAll()
//				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
			return http.build();
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
