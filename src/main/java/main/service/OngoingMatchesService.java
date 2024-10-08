package main.service;

import main.customExceptions.PlayerNotCreatedException;
import main.dao.PlayerDao;
import main.entities.MatchEntity;
import main.entities.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private PlayerDao playerDao;

    public OngoingMatchesService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    private static final Map<UUID, MatchEntity> ongoingMatches = new HashMap<>();

    public MatchEntity createMatch(String player1Name, String player2Name) throws PlayerNotCreatedException {

        PlayerEntity player1 = playerDao.getOrSavePlayer(player1Name);
        PlayerEntity player2 = playerDao.getOrSavePlayer(player2Name);

        MatchEntity newOngoingMatch = new MatchEntity(UUID.randomUUID(), player1, player2);
        ongoingMatches.put(newOngoingMatch.getMatchId(), newOngoingMatch);

        return newOngoingMatch;
    }

    public void update(MatchEntity ongoingMatch) {
        ongoingMatches.put(ongoingMatch.getMatchId(), ongoingMatch);
    }

    public MatchEntity getMatchById(UUID matchId) {
        return ongoingMatches.get(matchId);
    }

    public void removeMatchFromCollection(UUID matchId) {
        ongoingMatches.remove(matchId);
    }

}
