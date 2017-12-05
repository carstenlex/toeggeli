package com.toeggeli.toeggeli.match;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MatchRepository extends ReactiveMongoRepository<Match, String>{
}
