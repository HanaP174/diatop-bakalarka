package com.diatop.resource.service;

import com.diatop.model.user.User;
import com.diatop.model.user.UserDto;
import com.diatop.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Mono<List<UserDto>> getAllUsers() {
    return userRepository.findAllByRole("USER")
            .map(this::mapUserToUserDto)
            .collectList();
  }

  private UserDto mapUserToUserDto(User user) {
    return new UserDto(user.getBirthNumber(), user.getEmail(), user.getFirstName(), user.getLastName());
  }
}
