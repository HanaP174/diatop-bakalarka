package com.diatop.model.user;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

  Mono<User> findUserByEmail(String email);

  Mono<User> findUserById(Long id);

  Flux<User> findAllByRole(String role);
}
