package com.diatop.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

@SpringBootApplication
@EnableRedisWebSession
public class GatewayApplication {

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
        .route("default", p -> p.path("/")
          .filters(f -> f.setPath("http://localhost:8080/index.html"))
          .uri("http://localhost:8080/index.html"))
				.route("resource", p -> p.path("/resource/**")
						.filters(f -> f.rewritePath("/resource/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:9000"))
        .route("ui_home", p -> p.path("/ui/home", "/ui/account")
          .filters(f -> f.setPath("http://localhost:8081/index.html"))
          .uri("http://localhost:8081/index.html"))
				.route("ui", p -> p.path("/ui/**")
						.filters(f -> f.rewritePath("/ui/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:8081"))
        .route("admin_home", p -> p.path("/admin/home")
          .filters(f -> f.setPath("http://localhost:8082/index.html"))
          .uri("http://localhost:8082/index.html"))
				.route("admin", p -> p.path("/admin/**")
						.filters(f -> f.rewritePath("/admin/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:8082"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
