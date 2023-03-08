package com.example.gateway;

import com.example.gateway.model.UserPrincipal;
import io.netty.util.internal.NoOpTypeParameterMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

@SpringBootApplication
@RestController
public class GatewayApplication {

  @RequestMapping("/user")
  @ResponseBody
  public UserPrincipal user(Principal user) {
    UserPrincipal userPrincipal = new UserPrincipal();
    userPrincipal.setName(user.getName());
    userPrincipal.setRoles(AuthorityUtils.authorityListToSet(((Authentication) user).getAuthorities()));
    return userPrincipal;
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
				.route("admin", p -> p.path("/admin/**")
						.filters(f -> f.rewritePath("/admin/(?<segment>.*)", "/${segment}"))
						.uri("http://localhost:8082"))
				.build();
	}

	@Configuration
//  @Order(SecurityProperties.DEFAULT_FILTER_ORDER)
	@EnableWebSecurity
	protected static class SecurityConfiguration {

//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//      authProvider.setUserDetailsService(userDetailsService());
//      authProvider.setPasswordEncoder(passwordEncoder());
//      return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//      return http.getSharedObject(AuthenticationManagerBuilder.class)
//        .authenticationProvider(authProvider())
//        .build();
//    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

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

      return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
//        .httpBasic()
//        .and()
//        .logout()
//        .and()
        .authorizeRequests()
        .antMatchers("/index.html", "/", "/login", "/*.js", "/*.css", "/favicon.ico").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").permitAll() // todo disabled spring security
        .and()
        .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);


      return http.build();
		}

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
  }

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
