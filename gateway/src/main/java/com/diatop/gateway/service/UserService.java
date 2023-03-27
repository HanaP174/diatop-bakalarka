package com.diatop.gateway.service;

import com.diatop.model.user.User;
import com.diatop.model.user.UserDto;
import com.diatop.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // todo should check if user with the same email doesn't exist already
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

  private void mapUserDtoToUser(UserDto userDto, User user) {
    user.setEmail(userDto.getEmail());
    user.setUsername(userDto.getUsername());
    // todo this is not nice, enum will be better
    user.setRole("USER");
    user.setBirthNumber(userDto.getBirthNumber());
    user.setPassword(passwordEncoder().encode(userDto.getPassword()));
  }
}
