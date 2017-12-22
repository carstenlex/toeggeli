package com.toeggeli.toeggeli.team;

import com.toeggeli.toeggeli.core.BaseEntity;
import com.toeggeli.toeggeli.player.Player;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection="teams")
public class Team extends BaseEntity{
    private Player player1, player2;

    public Team(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public boolean is(String playerA, String playerB) {
        if (player1.getName().equals(playerA) && player2.getName().equals(playerB))
            return true;
        if (player2.getName().equals(playerA) && player1.getName().equals(playerB))
            return true;

        return false;
    }

    /**
     * Stellt fest, ob in zwei Teams nur verschiedene Spieler sind
     * @param other
     * @return
     */
    public boolean distinct(Team other){
        if (other.player1.getName().equals(player1.getName())
                || other.player2.getName().equals(player1.getName())
                || other.player1.getName().equals(player2.getName())
                || other.player2.getName().equals(player2.getName())
                ) {
            return false;
        }

        return true;
    }
}
