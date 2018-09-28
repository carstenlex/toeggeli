package com.toeggeli.toeggeli.statistik.elo;

class Player {
    String name;
    double elo;
    int wins;
    int looses;

    Player(String name){
        this.name = name;
        this.elo = 1000;
    }

    Player(Player player){
        this.name = player.name;
        this.elo = player.elo;
        this.wins = player.wins;
        this.looses = player.looses;
    }

    @Override
    public String toString() {
        return name +"("+elo+")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getElo() {
        return elo;
    }

    public void setElo(double elo) {
        this.elo = elo;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLooses() {
        return looses;
    }

    public void setLooses(int looses) {
        this.looses = looses;
    }
}