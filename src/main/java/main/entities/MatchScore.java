package main.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MatchScore {
    private UUID matchId;
    private Integer player1ID;
    private Integer player2ID;
    private Integer player1Sets;
    private Integer player2Sets;
    private Integer player1Games;
    private Integer player2Games;
    private Integer player1Points;
    private Integer player2Points;

    public MatchScore(UUID matchId, Integer player1ID, Integer player2ID) {
        this.matchId = matchId;
        this.player1ID = player1ID;
        this.player2ID = player2ID;
        this.player1Sets = 0;
        this.player2Sets = 0;
        this.player1Games = 0;
        this.player2Games = 0;
        this.player1Points = 0;
        this.player2Points = 0;
    }
}
