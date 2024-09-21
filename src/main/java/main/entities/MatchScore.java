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
    private Integer player1Sets;
    private Integer player2Sets;
    private Integer player1Games;
    private Integer player2Games;
    private Integer player1Points;
    private Integer player2Points;
    private PlayerEntity winner;
    private boolean isWinnerAssigned;

    public MatchScore(UUID matchId, PlayerEntity player1, PlayerEntity player2) {
        this.matchId = matchId;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Sets = 0;
        this.player2Sets = 0;
        this.player1Games = 0;
        this.player2Games = 0;
        this.player1Points = 0;
        this.player2Points = 0;
        this.winner = null;
        this.isWinnerAssigned = false;
    }
}
