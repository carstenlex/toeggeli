package com.toeggeli.toeggeli.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepo;

    @GetMapping
    public Flux<Player> listAllPlayers(){
        return playerRepo.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Player> findPlayer(@PathVariable("id") String id){
        return playerRepo.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePlayer(@PathVariable("id") String id){
        return playerRepo.deleteById(id);
    }

    @PostMapping
    public Mono<Player> createPlayer(@RequestBody Player player){
        Mono<Player> savedPlayer = playerRepo.save(player);

        return savedPlayer;
    }
}
