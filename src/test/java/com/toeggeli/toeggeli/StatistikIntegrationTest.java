package com.toeggeli.toeggeli;

import com.toeggeli.toeggeli.match.Match;
import com.toeggeli.toeggeli.match.MatchRepository;
import com.toeggeli.toeggeli.player.Player;
import com.toeggeli.toeggeli.player.PlayerRepository;
import com.toeggeli.toeggeli.team.Team;
import com.toeggeli.toeggeli.team.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatistikIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    MongoTemplate mongoDB;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Before
    public void init() {
        mongoDB.dropCollection("matches");
        mongoDB.dropCollection("players");
        mongoDB.dropCollection("teams");
        fillCollections();
    }

    private void fillCollections() {

        List<Player> players = createPlayers();

        List<Team> teams = createTeams(players);

        createMatches(teams);
    }

    private void createMatches(List<Team> teams) {
        Team emptyTeam = new Team(new Player(""),new Player(""));
        List<Team> lexcPencSet = teams.stream().filter(team -> team.is("Carsten", "Phong")).collect(Collectors.toList());
        Team lexcPenc = lexcPencSet.get(0);

        teams.stream().filter(team -> !team.equals(lexcPenc) && lexcPenc.distinct(team)).forEach(loser -> {
            Match m = new Match();
            m.setDatum(LocalDateTime.now());
            m.setTeam1(lexcPenc);
            m.setTeam2(loser);
            m.setScore1(10);
            m.setScore2(new Random().nextInt(9));
            matchRepository.save(m).subscribe();
        });
    }

    private List<Team> createTeams(List<Player> players) {
        Map<String, Player> playerMap = players.stream().collect(Collectors.toMap(Player::getId, player -> player));
        for(Player player1 : players) {
            playerMap.remove(player1.getId());
            for (Player player2: playerMap.values()){
                Team t = new Team(player1, player2);
                teamRepository.save(t).subscribe();
            }
        }


        return teamRepository.findAll().collectList().block();
    }

    private List<Player> createPlayers() {
        List<String> strings = Arrays.asList("Carsten", "Lars", "Marcel", "Phong", "Rene", "Peter", "Simon", "Ueli");
        strings.stream().map(Player::new).forEach(p -> playerRepository.save(p).subscribe());
        List<Player> allPlayers = playerRepository.findAll().collectList().block();
        return allPlayers;
    }


    @Test
    public void testTeamStatistik() {
         webTestClient.get().uri("/statistik/ranking/team")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(entity -> System.out.println(entity))
                 ;
    }

    @Test
    public void testPlayerStatistik() {
         webTestClient.get().uri("/statistik/ranking/player")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(entity -> System.out.println(entity))
                 ;
    }




}
