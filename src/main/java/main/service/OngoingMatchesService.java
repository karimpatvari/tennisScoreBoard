package main.service;

import main.customExceptions.MatchNotCreatedException;
import main.customExceptions.PlayerNotCreatedException;
import main.dao.PlayerDao;
import main.entities.MatchScore;
import main.entities.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private FinishedMatchesPersistenceService finishedMatchesService;
    private PlayerDao playerDao;

    //collection of ongoing matches
    private static Map<UUID, MatchScore> ongoingMatches = new HashMap<>();

    //constructors
    public OngoingMatchesService() {
    }

    public OngoingMatchesService(FinishedMatchesPersistenceService finishedMatchesService) {
        this.finishedMatchesService = finishedMatchesService;
    }

    public OngoingMatchesService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }


    public MatchScore createMatch(String player1Name, String player2Name) throws PlayerNotCreatedException {

        PlayerEntity player1 = playerDao.getOrSavePlayer(player1Name);
        PlayerEntity player2 = playerDao.getOrSavePlayer(player2Name);

        MatchScore newOngoingMatch = new MatchScore(UUID.randomUUID(), player1, player2);
        ongoingMatches.put(newOngoingMatch.getMatchId(), newOngoingMatch);

        return newOngoingMatch;
    }

    public void updateScore(MatchScore ongoingMatch) {
        ongoingMatches.put(ongoingMatch.getMatchId(), ongoingMatch);
    }

    public MatchScore getMatchById(UUID matchId) {
        return ongoingMatches.get(matchId);
    }

    public void removeMatchFromCollection(UUID matchId) {
        ongoingMatches.remove(matchId);
    }

    public void completeMatch(MatchScore matchScore) throws MatchNotCreatedException {
        //saving match record to db
        finishedMatchesService.saveMatchToDB(matchScore);
        //removing match from ongoing matches collection
        removeMatchFromCollection(matchScore.getMatchId());
    }


}
