package main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class newMatchErrorDto {
    private String errorMessage;
    private String player1NameValue;
    private String player2NameValue;
}
