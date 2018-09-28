package com.toeggeli.toeggeli.statistik.elo;

import java.util.List;

class EloSimulationObj {

    List<HistoryGame> historyGames;

    List<Player> playersSimRanking;

    public EloSimulationObj(List<HistoryGame> historyGames, List<Player> playersSimRanking) {
        this.historyGames = historyGames;
        this.playersSimRanking = playersSimRanking;
    }

    public List<HistoryGame> getHistoryGames() {
        return historyGames;
    }

    public void setHistoryGames(List<HistoryGame> historyGames) {
        this.historyGames = historyGames;
    }

    public List<Player> getPlayersSimRanking() {
        return playersSimRanking;
    }

    public void setPlayersSimRanking(List<Player> playersSimRanking) {
        this.playersSimRanking = playersSimRanking;
    }
}



