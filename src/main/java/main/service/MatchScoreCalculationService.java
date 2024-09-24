package main.service;

import main.entities.MatchEntity;
import main.entities.PlayerEntity;

public class MatchScoreCalculationService {

    public MatchEntity calculateMatch(MatchEntity ongoingMatch, String winnerId) {

        Integer winnerIdInteger = Integer.valueOf(winnerId);

        PlayerEntity winner = ongoingMatch.getPlayer1().getId() == winnerIdInteger ? ongoingMatch.getPlayer1() : ongoingMatch.getPlayer2();
        PlayerEntity opponent = ongoingMatch.getPlayer1().getId() == winnerIdInteger ? ongoingMatch.getPlayer2() : ongoingMatch.getPlayer1();

        if (winner.getGames() == 6 && opponent.getGames() == 6) {
            handleTieBreak(winner, ongoingMatch);
        } else {
            handleRegularPlay(winner, opponent, ongoingMatch);
        }

        ongoingMatch.assignWinner(winner);

        return ongoingMatch;
    }

    private void handleTieBreak(PlayerEntity winner, MatchEntity ongoingMatch) {
        winner.addTieBreakPoint();

        if (winner.getPoints() == 7){
            winner.winSet();
            ongoingMatch.resetGames();
        }
    }

    private void handleRegularPlay(PlayerEntity winner, PlayerEntity opponent, MatchEntity ongoingMatch) {
        
       if (winner.getPoints() < 40){
           winner.addPoint();
       }else{

           if (opponent.getPoints() == 40){

               if (winner.getPoints() == 40){
                   winner.addPoint();

               } else if (winner.getPoints() == 50) {
                   CompleteGame(winner,ongoingMatch);
                   checkSetWin(winner,opponent,ongoingMatch);
               }

           } else if (opponent.getPoints() == 50) {
               opponent.resetAdvantage();

           }else {
               CompleteGame(winner,ongoingMatch);
               checkSetWin(winner,opponent,ongoingMatch);
           }
       }


    }

    private void checkSetWin(PlayerEntity winner, PlayerEntity opponent, MatchEntity ongoingMatch) {
        if (winner.getGames() == 6 && opponent.getGames() < 5) {
            winner.winSet();
            ongoingMatch.resetGames();
            if (winner.getSets() == 2){
                ongoingMatch.assignWinner(winner);
            }
        }
    }

    private void CompleteGame(PlayerEntity winner, MatchEntity ongoingMatch) {
        winner.winGame();
        ongoingMatch.resetPoints();
    }

}
