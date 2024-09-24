package main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.entities.MatchEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class finishedMatchesDto {
    private List<matchDto> matches;
    private int page;
    private int totalPages;
}
