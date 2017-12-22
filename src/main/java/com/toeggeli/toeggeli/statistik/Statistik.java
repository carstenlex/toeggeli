package com.toeggeli.toeggeli.statistik;

import com.toeggeli.toeggeli.player.Player;
import com.toeggeli.toeggeli.team.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Statistik {

    private  Team team;
    private  long wins;
    private  long losses;
    private Player player;

    public Statistik(Team team, long wins, long losses) {
        this.team = team;
        this.wins = wins;
        this.losses = losses;
    }

    public Statistik(Player player, long wins, long losses ) {
        this.wins = wins;
        this.losses = losses;
        this.player = player;
    }
}
