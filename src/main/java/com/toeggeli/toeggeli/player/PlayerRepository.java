package com.toeggeli.toeggeli.player;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PlayerRepository extends ReactiveMongoRepository<Player, String> {
    Flux<Player> findByName(String name);
}
