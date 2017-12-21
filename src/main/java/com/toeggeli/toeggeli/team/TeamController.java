package com.toeggeli.toeggeli.team;

import com.toeggeli.toeggeli.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequestMapping("/team")
@RestController
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping
    public Flux<Team> listTeams() {
        return teamRepository.findAll();
    }

    @GetMapping("/ranking")
    public Flux<Team> listRankedTeams() {
        return teamRepository.findAll();
    }


    @PostMapping
    public ResponseEntity saveTeam(@RequestBody Team team) {
        if (team == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kein Team angegeben");
        }
        if (team.getPlayer1() == null || team.getPlayer2() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player1 und/oder Player2 fehlen");
        }
        if (team.getPlayer1().getId() == null || team.getPlayer2().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player1 und/oder Player2 müssen bereits existieren");
        }


        return ResponseEntity.ok(teamRepository.save(team));
    }


}
