package main.service;

import main.entities.MatchScore;

public class MatchScoreCalculationService {

    public static MatchScore calculateTheScore(MatchScore match, String isPlayer1winsPoint, String isPlayer2winsPoint) {

        Integer player1Points = match.getPlayer1Points();
        Integer player2Points = match.getPlayer2Points();


        if (isPlayer1winsPoint != null) {
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
                    Integer player1Games = match.getPlayer1Games();
                    Integer player1Sets = match.getPlayer1Sets();

                    if (player1Games < 6) {
                        player1Games++;
                        match.setPlayer1Games(player1Games);
                        match.setPlayer1Points(0);
                    }else if (player1Sets < 2){
                        player1Sets++;
                        match.setPlayer1Sets(player1Sets);
                        match.setPlayer1Points(0);
                        match.setPlayer1Games(0);
                    }

            }
        }

        if (isPlayer2winsPoint != null) {
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
                    Integer player2Games = match.getPlayer2Games();
                    Integer player2Sets = match.getPlayer2Sets();

                    if (player2Games < 6) {
                        player2Games++;
                        match.setPlayer2Games(player2Games);
                        match.setPlayer2Points(0);
                    }else if (player2Sets < 2){
                        player2Sets++;
                        match.setPlayer2Sets(player2Sets);
                        match.setPlayer2Points(0);
                        match.setPlayer2Games(0);
                    }
            }
        }

        return match;
    }


}
