package com.toeggeli.toeggeli.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/match")
@RestController
public class MatchController {

    @Autowired
    MatchRepository matchRepository;

    @GetMapping
    public Flux<Match> listMatches (){
        return matchRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Match> findMatch (@PathVariable("id") String id){
        return matchRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMatch (@PathVariable("id") String id){
        //TODO orphan - remove Teams? oder wegen History drin lassen
        return matchRepository.deleteById(id);
    }



    @PostMapping
    public Mono<Match> saveMatch(@RequestBody Match match){
        Mono<Match> save = matchRepository.save(match);
        //TODO upsert die beiden Teams
        return save;
    }
}
