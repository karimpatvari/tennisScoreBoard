package main.service;

import main.entities.MatchScore;
import main.entities.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private static Map<UUID, MatchScore> ongoingMatches = new HashMap<>();

    public MatchScore createMatch(PlayerEntity player1, PlayerEntity player2) {

        MatchScore newOngoingMatch = new MatchScore(UUID.randomUUID(),player1,player2);
        ongoingMatches.put(newOngoingMatch.getMatchId(),newOngoingMatch);

        return newOngoingMatch;
    }

    public void updateScore(MatchScore ongoingMatch) {
        MatchScore match = ongoingMatches.get(ongoingMatch.getMatchId());
        if (match != null) {
            match = ongoingMatch;
            ongoingMatches.replace(match.getMatchId(),ongoingMatch);
        }
    }

    public MatchScore getMatchById(UUID matchId) {
        return ongoingMatches.get(matchId);
    }

    public void removeMatchFromCollection(UUID matchId){
        ongoingMatches.remove(matchId);
    }

    public Map<UUID, MatchScore> getAllOngoingMatches() {
        return ongoingMatches;
    }

}
