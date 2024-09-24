package main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class matchScoreDto {

    private UUID matchId;

    private Integer player1Id;
    private String player1Name;
    private Integer player1Sets;
    private Integer player1Games;
    private Integer player1Points;

    private Integer player2Id;
    private String player2Name;
    private Integer player2Sets;
    private Integer player2Games;
    private Integer player2Points;
}
