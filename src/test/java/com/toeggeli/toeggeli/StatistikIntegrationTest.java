package com.toeggeli.toeggeli;

import com.toeggeli.toeggeli.statistik.Statistik;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatistikIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testTeamStatistik() {
         webTestClient.get().uri("/statistik/ranking/team")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                 .jsonPath("$[0].team.player1.name").isEqualTo("Marcel")
                 .jsonPath("$[0].team.player2.name").isEqualTo("Peter")
                 .jsonPath("$[0].wins").isEqualTo("1")
                 .jsonPath("$[0].losses").isEqualTo("1")
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
                 .jsonPath("$[0].player.name").isEqualTo("Marcel")
                 .jsonPath("$[0].wins").isEqualTo("1")
                 .jsonPath("$[0].losses").isEqualTo("1")
                            .consumeWith(entity -> System.out.println(entity))
                 ;
    }




}
