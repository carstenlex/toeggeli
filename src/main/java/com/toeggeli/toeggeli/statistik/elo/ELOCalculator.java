/*
package com.toeggeli.toeggeli.statistik.elo;




import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ELOCalculator {

    //https://math.stackexchange.com/questions/838809/rating-system-for-2-vs-2-2-vs-1-and-1-vs-1-game
    static double minEloWin = 2.631578947368418;
    static double maxEloWin = 50.0;
    static double minEloLose = -2.631578947368418;
    static double maxEloLose = -50.0;

    public static void main(String[] args){


        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get("D:\\WS\\toeggeli\\src\\main\\java\\com\\toeggeli\\toeggeli\\statistik\\elo\\matches.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray matchesJson = new JSONArray(content);

        Map<String, Player> players = new HashMap<>();
        ArrayList<Game> games = new ArrayList<>();

        for(int i = 0; i < matchesJson.length(); i++){
            JSONObject match = (JSONObject) matchesJson.get(i);
            JSONObject teamBlue = match.getJSONObject("team1");
            JSONObject teamRed = match.getJSONObject("team2");

            String p1Name = teamBlue.getJSONObject("player1").getString("name");
            String p2Name = teamBlue.getJSONObject("player2").getString("name");
            String p3Name = teamRed.getJSONObject("player1").getString("name");
            String p4Name = teamRed.getJSONObject("player2").getString("name");

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

            int scoreBlue = match.getInt("score1");
            int scoreRed = match.getInt("score2");

            */
/*System.out.println(p1Name +" "+ p2Name +" "+scoreBlue+":"+scoreRed+" "+ p3Name +" "+ p4Name);*//*


            games.add(new Game(players.get(p1Name),players.get(p2Name),players.get(p3Name),players.get(p4Name),scoreBlue, scoreRed));
        }
        System.out.println("Players: " + players.size());
        System.out.println("Games: " + games.size());

        for(Game game : games){
            playGame(game);
        }

        System.out.println("\nPlayers ELO:");
        Iterator it = players.entrySet().iterator();
        List<Player> playerList = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            playerList.add((Player)pair.getValue());

            it.remove(); // avoids a ConcurrentModificationException
        }
        playerList = playerList.stream().sorted((o1, o2) -> Double.compare(o2.elo, o1.elo) ).collect(Collectors.toList());

        playerList.forEach(System.out::println);

        double eloSum = 0;
        for(Player player : playerList){
            eloSum += player.elo;
        }
        System.out.println(eloSum);
        System.out.println(playerList.size() * 1000);




    }

    static void playGame(Game game){


        System.out.println(game.teamBlue.toString() +" VS " +game.teamRed.toString());
        System.out.println(game.teamBlue.score +":"+ game.teamRed.score);


        double probBlue = calcProbability(game.teamBlue.getTeamElo(), game.teamRed.getTeamElo());
        double probRed = calcProbability(game.teamRed.getTeamElo(), game.teamBlue.getTeamElo());

        double scoreBlue = calcScore(game.teamBlue.score, game.teamRed.score, game.teamBlue.win);
        double scoreRed = calcScore(game.teamRed.score, game.teamBlue.score, game.teamRed.win);

        double eloGainBlue = calcEloGain(scoreBlue,probBlue, game.teamBlue.win);
        double eloGainRed = calcEloGain(scoreRed,probRed, game.teamRed.win);


        for(Player player : game.teamBlue.players){
            System.out.println(player.toString() +" " +eloGainBlue);
            player.elo += eloGainBlue;
            if(game.teamBlue.win){
                player.wins += 1;
            } else {
                player.looses += 1;
            }
        }
        for(Player player : game.teamRed.players){
            System.out.println(player.toString() +" " +eloGainRed);

            player.elo += eloGainRed;
            if(game.teamRed.win){
                player.wins += 1;
            } else {
                player.looses += 1;
            }
        }

        System.out.println("\n");

    }

    static double calcProbability(double allyElo, double enemyElo){
*/
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

        return x5;*//*

        return (double)1/((Math.pow((double)10,(enemyElo-allyElo)/(double)500)) + (double)1);
    }

    static double calcScore(double allyctualScore, double enemyActualScore, boolean win){
        if(win){
           return (double)10 / ((double)10 + enemyActualScore);
        } else {
            return (1- ((double)10 / ((double)10 + allyctualScore)));
        }
    }
*/
/*
    static double calcScore(double allyctualScore, double enemyActualScore){
        return (allyctualScore - enemyActualScore) /10;
    }*//*



    static double calcEloGain(double score, double probability, boolean win) {
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
*/
