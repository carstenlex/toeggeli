package com.toeggeli.toeggeli.statistik;

import com.toeggeli.toeggeli.team.Team;
import lombok.Data;

@Data
public class ValueObject {

    private Team id;
    private WinLoss value;



    @Override
    public String toString() {
        return "ValueObject [id=" + id + ", value=" + value + "]";
    }
}

class WinLoss {
    int win, loss;

}