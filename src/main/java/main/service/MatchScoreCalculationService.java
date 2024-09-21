package main.service;

import main.entities.MatchScore;

public class MatchScoreCalculationService {

    public MatchScore calculateMatch(MatchScore ongoingMatch, String winnerId) {

        Integer player1ID = ongoingMatch.getPlayer1().getId();
        Integer player1Points = ongoingMatch.getPlayer1Points();
        Integer player1Games = ongoingMatch.getPlayer1Games();
        Integer player1Sets = ongoingMatch.getPlayer1Sets();

        Integer player2ID = ongoingMatch.getPlayer2().getId();
        Integer player2Points = ongoingMatch.getPlayer2Points();
        Integer player2Games = ongoingMatch.getPlayer2Games();
        Integer player2Sets = ongoingMatch.getPlayer2Sets();

        boolean isTieBreak = (player1Games == 6 && player2Games == 6);

        //player 1 wins a point
        if (player1ID.equals(Integer.valueOf(winnerId))) {
            if (isTieBreak) {
                ongoingMatch.setPlayer1Points(player1Points + 1);
                // Handle tiebreak win logic
                if (player1Points >= 6 && player1Points >= player2Points + 1) {
                    player1Sets++;
                    ongoingMatch.setPlayer1Sets(player1Sets);
                    resetSet(ongoingMatch);
                }
            } else {
                switch (player1Points) {
                    case 0:
                        ongoingMatch.setPlayer1Points(15);
                        break;
                    case 15:
                        ongoingMatch.setPlayer1Points(30);
                        break;
                    case 30:
                        ongoingMatch.setPlayer1Points(40);
                        break;
                    case 40:
                        if (player2Points == 40) { // Deuce scenario
                            // Handle advantage logic here if necessary
                            ongoingMatch.setPlayer1Points(50); // Represent advantage
                        } else if (player2Points == 50) {
                            ongoingMatch.setPlayer2Points(40); // Reset opponent's advantage
                        } else {
                            // Player 1 wins the game
                            player1Games++;
                            ongoingMatch.setPlayer1Games(player1Games);
                            resetGame(ongoingMatch);
                            if (player1Games == 6 && player2Games < 5) {
                                player1Sets++;
                                ongoingMatch.setPlayer1Sets(player1Sets);
                                resetSet(ongoingMatch);
                            }
                        }
                        break;
                    case 50: // Advantage case
                        player1Games++;
                        ongoingMatch.setPlayer1Games(player1Games);
                        resetGame(ongoingMatch);
                        if (player1Games == 6 && player2Games < 5) {
                            player1Sets++;
                            ongoingMatch.setPlayer1Sets(player1Sets);
                            resetSet(ongoingMatch);
                        }
                        break;
                }
            }
            if (player1Sets == 2){
                ongoingMatch.setWinner(ongoingMatch.getPlayer1());
                ongoingMatch.setWinnerAssigned(true);
            }
        }

        //player 2 wins a point
        if (player2ID.equals(Integer.valueOf(winnerId))) {
            if (isTieBreak) {
                ongoingMatch.setPlayer2Points(player2Points + 1);
                // Handle tiebreak win logic
                if (player2Points >= 6 && player2Points >= player1Points + 1) {
                    player2Sets++;
                    ongoingMatch.setPlayer2Sets(player2Sets);
                    resetSet(ongoingMatch);
                }
            } else {
                switch (player2Points) {
                    case 0:
                        ongoingMatch.setPlayer2Points(15);
                        break;
                    case 15:
                        ongoingMatch.setPlayer2Points(30);
                        break;
                    case 30:
                        ongoingMatch.setPlayer2Points(40);
                        break;
                    case 40:
                        if (player1Points == 40) { // Deuce scenario
                            ongoingMatch.setPlayer2Points(50); // Represent advantage
                        } else if (player1Points == 50) {
                            ongoingMatch.setPlayer1Points(40); // Reset opponent's advantage
                        } else {
                            // Player 2 wins the game
                            player2Games++;
                            ongoingMatch.setPlayer2Games(player2Games);
                            resetGame(ongoingMatch);
                            if (player2Games == 6 && player1Games < 5) {
                                player2Sets++;
                                ongoingMatch.setPlayer2Sets(player2Sets);
                                resetSet(ongoingMatch);
                            }
                        }
                        break;
                    case 50: // Advantage case
                        player2Games++;
                        ongoingMatch.setPlayer2Games(player2Games);
                        resetGame(ongoingMatch);
                        if (player2Games == 6 && player1Games < 5) {
                            player2Sets++;
                            ongoingMatch.setPlayer2Sets(player2Sets);
                            resetSet(ongoingMatch);
                        }
                        break;
                }
            }
            if (player2Sets == 2){
                ongoingMatch.setWinner(ongoingMatch.getPlayer2());
                ongoingMatch.setWinnerAssigned(true);
            }
        }
        
        return ongoingMatch;
    }

    private void resetGame(MatchScore match) {
        match.setPlayer1Points(0);
        match.setPlayer2Points(0);
    }
    
    private void resetSet(MatchScore match) {
        match.setPlayer1Games(0);
        match.setPlayer2Games(0);
        resetGame(match);
    }
    
}
