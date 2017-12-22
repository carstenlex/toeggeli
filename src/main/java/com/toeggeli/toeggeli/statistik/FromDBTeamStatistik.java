package com.toeggeli.toeggeli.statistik;

import com.toeggeli.toeggeli.team.Team;
import lombok.Data;

@Data
public class FromDBTeamStatistik {

    private Team id;
    private WinLoss value;



    @Override
    public String toString() {
        return "FromDBTeamStatistik [id=" + id + ", value=" + value + "]";
    }
}
