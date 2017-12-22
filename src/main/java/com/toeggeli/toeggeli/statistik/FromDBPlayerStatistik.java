package com.toeggeli.toeggeli.statistik;

import com.toeggeli.toeggeli.player.Player;
import lombok.Data;

@Data
public class FromDBPlayerStatistik {

    private Player id;
    private WinLoss value;


    @Override
    public String toString() {
        return "FromDBTeamStatistik [id=" + id + ", value=" + value + "]";
    }
}
