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
}
