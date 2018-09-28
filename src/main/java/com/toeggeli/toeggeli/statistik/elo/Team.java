package com.toeggeli.toeggeli.statistik.elo;

import java.util.ArrayList;

class Team {
    ArrayList<Player> players = new ArrayList<>();
    int score;
    boolean win;

    public Team(Player p1, Player p2) {
        players.add(p1);
        players.add(p2);
    }

    public Team(Team team){
        this.score = team.score;
        this.win = team.win;
        this.players = new ArrayList<>();
        for(Player player : team.players){
            this.players.add(new Player(player));
        }
    }

    double getTeamElo(){
        double elo = 0;
        for(Player player : players){
            elo += player.elo;
        }
        elo = elo/players.size();
        return elo;
    }

    @Override
    public String toString() {
        return players.toString() +"["+getTeamElo()+"]";
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}