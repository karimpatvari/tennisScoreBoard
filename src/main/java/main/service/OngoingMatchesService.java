package main.service;

import main.entities.Match;
import main.entities.MatchScore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private static Map<UUID, MatchScore> ongoingMatches = new HashMap<>();

    public static MatchScore createMatch(Integer player1ID, Integer player2ID) {
        MatchScore newOngoingMatch = new MatchScore(UUID.randomUUID(),player1ID,player2ID);
        ongoingMatches.put(newOngoingMatch.getMatchId(),newOngoingMatch);
        return newOngoingMatch;
    }

    public static void updateScore(MatchScore ongoingMatch) {
        MatchScore match = ongoingMatches.get(ongoingMatch.getMatchId());
        if (match != null) {
            match = ongoingMatch;
            ongoingMatches.replace(match.getMatchId(),ongoingMatch);
        }
    }

    public static MatchScore getMatchById(UUID matchId) {
        return ongoingMatches.get(matchId);
    }

    public static Map<UUID, MatchScore> getAllOngoingMatches() {
        return ongoingMatches;
    }

}
