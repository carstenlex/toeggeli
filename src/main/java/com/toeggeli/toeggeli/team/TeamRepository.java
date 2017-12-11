package com.toeggeli.toeggeli.team;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TeamRepository extends ReactiveMongoRepository<Team, String>{

    @Query("{ $and:[{ '$or' : [{'player1.name': ?0} ,{'player2.name': ?0 } ] },  {'$or' : [{'player1.name': ?1} ,{'player2.name': ?1 } ] } ] }")
    Mono<Team> findByPlayers(String namePlayer1, String namePlayer2);
}
