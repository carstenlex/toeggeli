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
}
