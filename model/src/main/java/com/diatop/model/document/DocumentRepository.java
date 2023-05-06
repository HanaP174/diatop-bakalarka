package com.diatop.model.document;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DocumentRepository extends ReactiveCrudRepository<Document, Integer> {

  Mono<Void> deleteByUid(String uid);
}
