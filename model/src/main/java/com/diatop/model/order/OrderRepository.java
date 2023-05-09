package com.diatop.model.order;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {

  Flux<Order> findAllByUserId(Long userId);
}
