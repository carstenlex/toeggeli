package com.toeggeli.toeggeli.statistik.elo;

class HistoryGame {

    Team teamBlue;
    Team teamRed;

    double eloGainBlue;
    double eloGainRed;

    public Team getTeamBlue() {
        return teamBlue;
    }

    public void setTeamBlue(Team teamBlue) {
        this.teamBlue = teamBlue;
    }

    public Team getTeamRed() {
        return teamRed;
    }

    public void setTeamRed(Team teamRed) {
        this.teamRed = teamRed;
    }

    public double getEloGainBlue() {
        return eloGainBlue;
    }

    public void setEloGainBlue(double eloGainBlue) {
        this.eloGainBlue = eloGainBlue;
    }

    public double getEloGainRed() {
        return eloGainRed;
    }

    public void setEloGainRed(double eloGainRed) {
        this.eloGainRed = eloGainRed;
    }
}
