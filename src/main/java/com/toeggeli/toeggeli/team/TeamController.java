package com.toeggeli.toeggeli.team;

import com.toeggeli.toeggeli.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/team")
@RestController
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping
    public Flux<Team> listTeams() {
        return teamRepository.findAll();
    }


    @PostMapping
    public Mono<Team> saveTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }


    @GetMapping("/generatedata")
    public ResponseEntity generateDate() {
        List<Player> players1 = Arrays.asList("Carsten","Phong","Peter","Ueli","Marcel","Rene","Lars").stream().map(Player::new).collect(Collectors.toList());
        List<Player> players2 = players1;

        List<Team> teams = new ArrayList<>();
        for(Player p1: players1) {
            for (Player p2: players2){
                if (p1.getName().equalsIgnoreCase(p2.getName())){
                    continue;
                }
                Team team = new Team(p1,p2);
                teams.add(team);
            }
        }
        Flux<Team> teamFlux = teamRepository.saveAll(teams);
        return ResponseEntity.ok(teamFlux);
    }
}
