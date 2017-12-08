package com.toeggeli.toeggeli;


import com.toeggeli.toeggeli.player.Player;
import com.toeggeli.toeggeli.player.PlayerRepository;
import com.toeggeli.toeggeli.team.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebIntegrationTests {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    PlayerRepository playerRepository;


    @Before
    public void clearCollections () {
        Mono<Void> voidMono = playerRepository.deleteAll();
        voidMono.subscribe();
    }

    @Test
    public void testCreatePlayer() {
        Player player = new Player("Carsten");

        webTestClient.post().uri("/player")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(player), Player.class)
                    .exchange()
                    .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Carsten");
    }

    @Test
    public void testListAllPlayers () {
        List<Player> players = Arrays.asList("Carsten", "Phong", "Lars", "Ueli", "Rene", "Peter", "Simon", "Marcel")
                .stream().map(Player::new).collect(Collectors.toList());

        Flux<Player> playerFlux = playerRepository.saveAll(players);
        Mono<List<Player>> listMono = playerFlux.collectList();
        listMono.subscribe(playerList -> {
           webTestClient.get().uri("/player")
                   .accept(MediaType.APPLICATION_JSON_UTF8)
                   .exchange()
                   .expectStatus().isOk()
                   .expectBody();
        });
    }


    @Test
    public void createTeam() {
        List<Player> players = Arrays.asList("Carsten", "Phong")
                .stream().map(Player::new).collect(Collectors.toList());

        Flux<Player> playerFlux = playerRepository.saveAll(players);
        Mono<List<Player>> listMono = playerFlux.collectList();
        listMono.subscribe(playerList -> {
            Team team = new Team(playerList.get(0), playerList.get(1));
            String s = webTestClient.post().uri("/team")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .body(Mono.just(team), Team.class)

                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .toString();
            System.out.println(s);
        });
        System.out.println("Harry");
    }

}
