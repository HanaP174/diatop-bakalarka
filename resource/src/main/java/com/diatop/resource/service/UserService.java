package com.diatop.resource.service;

import com.diatop.model.user.User;
import com.diatop.model.user.UserDto;
import com.diatop.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public Mono<List<UserDto>> getAllUsers() {
    return userRepository.findAllByRole("USER")
            .map(this::mapUserToUserDto)
            .collectList();
  }

  private UserDto mapUserToUserDto(User user) {
    return new UserDto(user.getBirthNumber(), user.getEmail(), user.getFirstName(), user.getLastName());
  }

  public Mono<User> changeUser(UserDto userDto) {
    return userRepository.findUserById(userDto.getId()).flatMap(existingUser -> {
      existingUser.setFirstName(userDto.getFirstName());
      existingUser.setLastName(userDto.getLastName());
      existingUser.setEmail(userDto.getEmail());
      existingUser.setBirthNumber(userDto.getBirthNumber());
      if (userDto.getPassword() != null) {
        existingUser.setPassword(passwordEncoder().encode(userDto.getPassword()));
      }
      return userRepository.save(existingUser);
    });
  }
}
