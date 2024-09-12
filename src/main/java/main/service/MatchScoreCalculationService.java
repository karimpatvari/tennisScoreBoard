package main.service;

import main.entities.MatchScore;

public class MatchScoreCalculationService {

    public static MatchScore calculateTheScore(MatchScore match, String isPlayer1winsPoint, String isPlayer2winsPoint) {

        Integer player1Points = match.getPlayer1Points();
        Integer player2Points = match.getPlayer2Points();

        Integer player1Games = match.getPlayer1Games();
        Integer player2Games = match.getPlayer2Games();

        Integer player1Sets = match.getPlayer1Sets();
        Integer player2Sets = match.getPlayer2Sets();

        if ()

        boolean isTiebreak = (player1Games == 6 && player2Games == 6);

        // Player 1 scores a point
        if (isPlayer1winsPoint != null) {
            if (isTiebreak) {
                match.setPlayer1Points(player1Points + 1);
                // Handle tiebreak win logic
                if (player1Points >= 6 && player1Points >= player2Points + 1) {
                    player1Sets++;
                    match.setPlayer1Sets(player1Sets);
                    resetSet(match);
                }
            } else {
                switch (player1Points) {
                    case 0:
                        match.setPlayer1Points(15);
                        break;
                    case 15:
                        match.setPlayer1Points(30);
                        break;
                    case 30:
                        match.setPlayer1Points(40);
                        break;
                    case 40:
                        if (player2Points == 40) { // Deuce scenario
                            // Handle advantage logic here if necessary
                            match.setPlayer1Points(50); // Represent advantage
                        } else if (player2Points == 50) {
                            match.setPlayer2Points(40); // Reset opponent's advantage
                        } else {
                            // Player 1 wins the game
                            player1Games++;
                            match.setPlayer1Games(player1Games);
                            resetGame(match);
                            if (player1Games == 6 && player2Games < 5) {
                                player1Sets++;
                                match.setPlayer1Sets(player1Sets);
                                resetSet(match);
                            }
                        }
                        break;
                    case 50: // Advantage case
                        player1Games++;
                        match.setPlayer1Games(player1Games);
                        resetGame(match);
                        if (player1Games == 6 && player2Games < 5) {
                            player1Sets++;
                            match.setPlayer1Sets(player1Sets);
                            resetSet(match);
                        }
                        break;
                }
            }
        }

        // Player 2 scores a point
        if (isPlayer2winsPoint != null) {
            if (isTiebreak) {
                match.setPlayer2Points(player2Points + 1);
                // Handle tiebreak win logic
                if (player2Points >= 6 && player2Points >= player1Points + 1) {
                    player2Sets++;
                    match.setPlayer2Sets(player2Sets);
                    resetSet(match);
                }
            } else {
                switch (player2Points) {
                    case 0:
                        match.setPlayer2Points(15);
                        break;
                    case 15:
                        match.setPlayer2Points(30);
                        break;
                    case 30:
                        match.setPlayer2Points(40);
                        break;
                    case 40:
                        if (player1Points == 40) { // Deuce scenario
                            match.setPlayer2Points(50); // Represent advantage
                        } else if (player1Points == 50) {
                            match.setPlayer1Points(40); // Reset opponent's advantage
                        } else {
                            // Player 2 wins the game
                            player2Games++;
                            match.setPlayer2Games(player2Games);
                            resetGame(match);
                            if (player2Games == 6 && player1Games < 5) {
                                player2Sets++;
                                match.setPlayer2Sets(player2Sets);
                                resetSet(match);
                            }
                        }
                        break;
                    case 50: // Advantage case
                        player2Games++;
                        match.setPlayer2Games(player2Games);
                        resetGame(match);
                        if (player2Games == 6 && player1Games < 5) {
                            player2Sets++;
                            match.setPlayer2Sets(player2Sets);
                            resetSet(match);
                        }
                        break;
                }
            }
        }

        return match;
    }

    // Helper method to reset points after a game
    private static void resetGame(MatchScore match) {
        match.setPlayer1Points(0);
        match.setPlayer2Points(0);
    }

    // Helper method to reset games after a set
    private static void resetSet(MatchScore match) {
        match.setPlayer1Games(0);
        match.setPlayer2Games(0);
        resetGame(match);
    }


}
