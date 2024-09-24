package main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class finalScoreDto {
    private String player1Name;
    private Integer player1Sets;
    private String player2Name;
    private Integer player2Sets;
    private String winnerName;
}
