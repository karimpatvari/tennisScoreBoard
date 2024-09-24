package main.utils;

import main.dto.matchDto;
import main.entities.MatchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "player1.name", target = "player1Name"),
            @Mapping(source = "player2.name", target = "player2Name"),
            @Mapping(source = "winner.name", target = "winnerName")
    })
    matchDto matchEntityToMatchDto(MatchEntity match);

    default List<matchDto> matchEntitiesToMatchDtos(List<MatchEntity> matchEntities){
        List<matchDto> matchDtos = new ArrayList<>();
        for (MatchEntity matchEntity : matchEntities) {
            matchDtos.add(matchEntityToMatchDto(matchEntity));
        }
        return matchDtos;
    }

}
