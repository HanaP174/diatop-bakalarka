package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class GatewayApplication {

  @RequestMapping("/user")
  public Principal user(Principal user) {
    return user;
  }

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("resource", p -> p.path("/resource/**")
						.filters(f -> f.rewritePath("/resource/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:9000"))
				.route("ui", p -> p.path("/ui/**")
						.filters(f -> f.rewritePath("/ui/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:8081"))
				.build();
	}

	@Configuration
  @Order(SecurityProperties.DEFAULT_FILTER_ORDER)
	@EnableWebSecurity
	protected static class SecurityConfiguration {
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
        .httpBasic()
        .and()
//        .logout()
//        .and()
        .authorizeRequests()
        .antMatchers("/index.html", "/", "/login", "/*.js", "/*.css").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        .and()
        .formLogin().loginPage("/login").permitAll(); // todo disabled spring security login after reload
      return http.build();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
