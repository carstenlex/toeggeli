package com.toeggeli.toeggeli.match;

import com.toeggeli.toeggeli.team.Team;
import com.toeggeli.toeggeli.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDateTime;

@CrossOrigin
@RequestMapping("/match")
@RestController
public class MatchController {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    @GetMapping
    public Flux<Match> listMatches() {
        return matchRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Match> findMatch(@PathVariable("id") String id) {
        return matchRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMatch(@PathVariable("id") String id) {
        //TODO orphan - remove Teams? oder wegen History drin lassen
        return matchRepository.deleteById(id);
    }


    @PostMapping
    public Mono<Match> saveMatch(@RequestBody Match match) {
        //FIXME das ist die blocking-variante
        Team team1 = findOrCreateTeamBLOCKED(match.getTeam1());
        Team team2 = findOrCreateTeamBLOCKED(match.getTeam2());
        match.setTeam2(team2);
        match.setTeam1(team1);
        match.setDatum(LocalDateTime.now());
        Mono<Match> save = matchRepository.save(match);
        return save;

    }


    public Mono<Match> saveMatchReactive(@RequestBody Match match) {
        Scheduler scheduler = null; //FIXME hier den lookup in der DB suchen
        Flux<Team> lookedUpTeams = Flux.just(match.getTeam1(), match.getTeam2())
                .flatMap(team -> Flux.just(team).subscribeOn(scheduler));

//        lookedUpTeams.
        return Mono.just(null);
    }

    private Team findOrCreateTeamBLOCKED(Team team) {
        if (team.getId() != null) {
            return teamRepository.findById(team.getId()).block();
        }
        Team byPlayers = teamRepository.findByPlayers(team.getPlayer1().getName(), team.getPlayer2().getName()).block();
        if (byPlayers == null) {
            return teamRepository.save(team).block();
        } else {
            return byPlayers;
        }
    }
}
