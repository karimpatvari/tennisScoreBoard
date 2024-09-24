package main.utils;

import main.dto.finalScoreDto;
import main.dto.matchScoreDto;
import main.entities.MatchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchScoreMapper {

    MatchScoreMapper INSTANCE  = Mappers.getMapper(MatchScoreMapper.class);

    @Mappings({
            @Mapping(source = "matchId", target = "matchId"),
            @Mapping(source = "player1.id", target = "player1Id"),
            @Mapping(source = "player1.name", target = "player1Name"),
            @Mapping(source = "player1.sets", target = "player1Sets"),
            @Mapping(source = "player1.games", target = "player1Games"),
            @Mapping(source = "player1.points", target = "player1Points"),
            @Mapping(source = "player2.id", target = "player2Id"),
            @Mapping(source = "player2.name", target = "player2Name"),
            @Mapping(source = "player2.sets", target = "player2Sets"),
            @Mapping(source = "player2.games", target = "player2Games"),
            @Mapping(source = "player2.points", target = "player2Points")
    })

    matchScoreDto matchEntityToMatchScoreDto(MatchEntity matchEntity);

    @Mappings({
            @Mapping(source = "player1.name", target = "player1Name"),
            @Mapping(source = "player1.sets", target = "player1Sets"),
            @Mapping(source = "player2.name", target = "player2Name"),
            @Mapping(source = "player2.sets", target = "player2Sets"),
            @Mapping(source = "winner.name", target = "winnerName")
    })
    finalScoreDto MatchEntityToFinalScoreDto(MatchEntity matchEntity);


}
