package com.diatop.gateway.service;

import com.diatop.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class UserDetailsService implements ReactiveUserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return userRepository.findUserByEmail(username).map(user -> User.builder()
      .username(user.getEmail())
      .password(user.getPassword())
      .roles(user.getRole())
      .build());
  }

//  // todo just for testing
//  @PostConstruct
//  private void initUsers() {
//    userRepository.deleteAll().subscribe();
//    com.diatop.model.user.User admin = new com.diatop.model.user.User(
//      passwordEncoder().encode("pass"),
//      "123456/1030",
//      "admin@admin.com",
//      "ADMIN");
//    com.diatop.model.user.User user = new com.diatop.model.user.User(
//      passwordEncoder().encode("pass"),
//      "123456/1031",
//      "user@user.com",
//      "USER");
//    userRepository.saveAll(Arrays.asList(admin, user)).subscribe();
//  }
}
