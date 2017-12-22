package com.toeggeli.toeggeli;

import com.toeggeli.toeggeli.player.Player;
import com.toeggeli.toeggeli.team.Team;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TeamUnitTest {

    @Test
    public void testTeamIs() {

        Player carsten = new Player("Carsten");
        Player phong = new Player ("Phong");
        Team lexcPenc = new Team(carsten, phong);

        assertTrue(lexcPenc.is("Carsten","Phong"));
        assertTrue(lexcPenc.is("Phong","Carsten"));
    }
}
