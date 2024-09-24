package main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class matchDto {
    private Integer id;
    private String player1Name;
    private String player2Name;
    private String winnerName;
}
