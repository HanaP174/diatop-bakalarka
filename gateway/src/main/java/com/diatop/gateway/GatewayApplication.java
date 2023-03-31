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
				.route("resource", p -> p.path("/resource/**")
						.filters(f -> f.rewritePath("/resource/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:9000"))
				.route("ui", p -> p.path("/ui/**")
						.filters(f -> f.rewritePath("/ui/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:8081"))
				.route("admin", p -> p.path("/admin/**")
						.filters(f -> f.rewritePath("/admin/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:8082"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
