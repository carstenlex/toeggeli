package com.toeggeli.toeggeli.statistik.elo;

class Game {

    Team teamBlue;
    Team teamRed;

    Game(Player p1, Player p2, Player p3, Player p4, int blueScore, int redScore) {
        teamBlue = new Team(p1, p2);
        teamRed = new Team(p3, p4);
        teamBlue.score = blueScore;
        teamRed.score = redScore;
        if(blueScore == 10){
            teamBlue.win = true;
        }else{
            teamRed.win = true;
        }
    }
}
