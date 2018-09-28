package com.toeggeli.toeggeli.statistik.elo;

import com.toeggeli.toeggeli.match.Match;
import com.toeggeli.toeggeli.match.MatchRepository;
import com.toeggeli.toeggeli.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EloService {

    private final double minEloWin = 2.631578947368418;
    private final double maxEloWin = 50.0;
    private final double minEloLose = -2.631578947368418;
    private final double maxEloLose = -50.0;

    @Autowired
    MatchRepository matchRepository;

    public EloSimulationObj simulateElo(){

       List<Match> matcheList = matchRepository.findAll().collectList().block();

       /*
       for(Match match : matcheList){
           System.out.println(match.getTeam1().toString() +" VS " +match.getTeam2().toString());
           System.out.println(match.getScore1() +":"+ match.getScore2());
       }
       */


        Map<String, Player> players = new HashMap<>();
        ArrayList<Game> games = new ArrayList<>();

        for(Match match : matcheList){

            Team teamBlue = match.getTeam1();
            Team teamRed = match.getTeam2();

            String p1Name = teamBlue.getPlayer1().getName();
            String p2Name = teamBlue.getPlayer2().getName();
            String p3Name = teamRed.getPlayer1().getName();
            String p4Name = teamRed.getPlayer2().getName();


            if(!players.containsKey(p1Name)){
                players.put(p1Name, new Player(p1Name));
            }

            if(!players.containsKey(p2Name)){
                players.put(p2Name, new Player(p2Name));
            }

            if(!players.containsKey(p3Name)){
                players.put(p3Name, new Player(p3Name));
            }

            if(!players.containsKey(p4Name)){
                players.put(p4Name, new Player(p4Name));
            }

            int scoreBlue = match.getScore1();
            int scoreRed = match.getScore2();

            /*System.out.println(p1Name +" "+ p2Name +" "+scoreBlue+":"+scoreRed+" "+ p3Name +" "+ p4Name);*/

            games.add(new Game(players.get(p1Name),players.get(p2Name),players.get(p3Name),players.get(p4Name),scoreBlue, scoreRed));
        }
        /*
        System.out.println("Players: " + players.size());
        System.out.println("Games: " + games.size());
        */

        ArrayList<HistoryGame> historyGames = new ArrayList<>();

        for(Game game : games){
            historyGames.add(playGame(game));
        }

        //System.out.println("\nPlayers ELO:");
        Iterator it = players.entrySet().iterator();
        List<Player> playerList = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            playerList.add((Player)pair.getValue());

            it.remove(); // avoids a ConcurrentModificationException
        }
        playerList = playerList.stream().sorted((o1, o2) -> Double.compare(o2.elo, o1.elo) ).collect(Collectors.toList());

        //playerList.forEach(System.out::println);

        double eloSum = 0;
        for(Player player : playerList){
            eloSum += player.elo;
        }
        /*
        System.out.println(eloSum);
        System.out.println(playerList.size() * 1000);
        */

        EloSimulationObj eloSimulationObj = new EloSimulationObj(historyGames, playerList);

       return eloSimulationObj;
    }


    private HistoryGame playGame(Game game){

        HistoryGame historyGame = new HistoryGame();

        historyGame.teamBlue = new com.toeggeli.toeggeli.statistik.elo.Team(game.teamBlue);
        historyGame.teamRed = new com.toeggeli.toeggeli.statistik.elo.Team(game.teamRed);

        /*
        System.out.println(game.teamBlue.toString() +" VS " +game.teamRed.toString());
        System.out.println(game.teamBlue.score +":"+ game.teamRed.score);
        */

        double probBlue = calcProbability(game.teamBlue.getTeamElo(), game.teamRed.getTeamElo());
        double probRed = calcProbability(game.teamRed.getTeamElo(), game.teamBlue.getTeamElo());

        double scoreBlue = calcScore(game.teamBlue.score, game.teamRed.score, game.teamBlue.win);
        double scoreRed = calcScore(game.teamRed.score, game.teamBlue.score, game.teamRed.win);

        double eloGainBlue = calcEloGain(scoreBlue,probBlue, game.teamBlue.win);
        double eloGainRed = calcEloGain(scoreRed,probRed, game.teamRed.win);

        historyGame.eloGainBlue = eloGainBlue;
        historyGame.eloGainRed = eloGainRed;


        for(Player player : game.teamBlue.players){
            //System.out.println(player.toString() +" " +eloGainBlue);
            player.elo += eloGainBlue;
            if(game.teamBlue.win){
                player.wins += 1;
            } else {
                player.looses += 1;
            }
        }
        for(Player player : game.teamRed.players){
            //System.out.println(player.toString() +" " +eloGainRed);

            player.elo += eloGainRed;
            if(game.teamRed.win){
                player.wins += 1;
            } else {
                player.looses += 1;
            }
        }

        //System.out.println("\n");

        return historyGame;

    }

    private double calcProbability(double allyElo, double enemyElo){
/*        double x1 = enemyElo-allyElo;
        if(x1 >= (double)500){
            x1 = 500;
        } else if(x1 <= (double)-500){
            x1 = -500;
        }
        double x2 = x1 / (double)500;
        double x3 = Math.pow((double)10, x2);
        double x4 = x3 + (double)1;
        double x5 = (double)1/x4;

        return x5;*/
        return (double)1/((Math.pow((double)10,(enemyElo-allyElo)/(double)500)) + (double)1);
    }

    private double calcScore(double allyctualScore, double enemyActualScore, boolean win){
        if(win){
            return (double)10 / ((double)10 + enemyActualScore);
        } else {
            return (1- ((double)10 / ((double)10 + allyctualScore)));
        }
    }
/*
    static double calcScore(double allyctualScore, double enemyActualScore){
        return (allyctualScore - enemyActualScore) /10;
    }*/


    private double calcEloGain(double score, double probability, boolean win) {
        double elo;
        elo = 100 * (score - probability);
        if(win){
            if(elo > maxEloWin){
                elo = maxEloWin;
            }else if(elo < minEloWin){
                elo = minEloWin;
            }
        }else {
            if(elo<maxEloLose){
                elo = maxEloLose;
            }else if(elo>minEloLose){
                elo = minEloLose;
            }
        }
        return  elo;

        //TODO min max ELO
    }

}
