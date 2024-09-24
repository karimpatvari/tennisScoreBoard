package main.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MatchScore {

    private UUID matchId;
    private PlayerEntity player1;
    private PlayerEntity player2;
    private PlayerEntity winner;
    private boolean isWinnerAssigned;

    public MatchScore(UUID matchId, PlayerEntity player1, PlayerEntity player2) {
        this.matchId = matchId;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = null;
        this.isWinnerAssigned = false;
    }

    public void resetPoints(){
        this.player1.setPoints(0);
        this.player2.setPoints(0);
    }

    public void resetGames(){
        this.player1.setGames(0);
        this.player2.setGames(0);
        resetPoints();
    }

    public void assignWinner(PlayerEntity winner){
        if (winner.getSets() == 2){
            this.winner = winner;
            isWinnerAssigned = true;
        }
    }

}
