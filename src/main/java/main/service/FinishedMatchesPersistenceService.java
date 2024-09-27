package main.service;

import main.customExceptions.MatchNotCreatedException;
import main.dao.MatchDao;
import main.entities.MatchEntity;

import java.util.List;

public class FinishedMatchesPersistenceService {

    private MatchDao matchDao;

    public FinishedMatchesPersistenceService(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    public MatchEntity saveMatchToDB(MatchEntity matchScore) throws MatchNotCreatedException {

        MatchEntity matchEntity = new MatchEntity(
                matchScore.getPlayer1(),
                matchScore.getPlayer2(),
                matchScore.getWinner()
        );

        return matchDao.createMatch(matchEntity);
    }

    public List<MatchEntity> GetMatchesFromDB(int pageNum, int recordsPerPage) {

        if (pageNum < 1) pageNum = 1;  // Ensure page number is positive

        // Calculate offset based on page number and records per page
        int offset = (pageNum - 1) * recordsPerPage;

        // Fetch matches for the given page from the DAO
        List<MatchEntity> paginatedMatchEntities = matchDao.getMatchesWithPagination(offset, recordsPerPage);

        return paginatedMatchEntities;
    }

    public int getTotalMatchCount() {
        return matchDao.getTotalMatchCount();
    }

    public List<MatchEntity> getMatchesByPlayerName(String playerName) {
        List<MatchEntity> matchesByPlayerName = matchDao.getMatchesByPlayerName(playerName);
        return matchesByPlayerName;
    }

}
