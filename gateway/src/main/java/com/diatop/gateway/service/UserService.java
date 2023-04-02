package com.diatop.gateway.service;

import com.diatop.model.user.User;
import com.diatop.model.user.UserDto;
import com.diatop.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public Mono<Boolean> addUser(UserDto userDto) {
    return userRepository.findUserByEmail(userDto.getEmail())
      .hasElement()
      .flatMap(userExists -> {
        if (userExists) {
          return Mono.just(false);
        } else {
          User user = new User();
          mapUserDtoToUser(userDto, user);
          return userRepository.save(user).map(createdUser -> true);
        }
      });
  }

  public Mono<Long> getUserId() {
    return ReactiveSecurityContextHolder.getContext()
      .map(SecurityContext::getAuthentication)
      .map(Principal::getName)
      .flatMap(username -> {
        if (username != null) {
          return userRepository.findUserByEmail(username)
            .flatMap(user -> Mono.justOrEmpty(user.getId()));
        }
        return Mono.empty();
      });
  }

  private void mapUserDtoToUser(UserDto userDto, User user) {
    user.setEmail(userDto.getEmail());
    // todo this is not nice, enum will be better
    user.setRole("USER");
    user.setBirthNumber(userDto.getBirthNumber());
    user.setPassword(passwordEncoder().encode(userDto.getPassword()));
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
  }
}
