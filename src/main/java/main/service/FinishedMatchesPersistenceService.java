package main.service;

import main.customExceptions.MatchNotCreatedException;
import main.dao.MatchDao;
import main.entities.MatchEntity;
import main.entities.MatchScore;
import main.entities.PlayerEntity;
import java.util.List;

public class FinishedMatchesPersistenceService {

    // Save a match to the database
    public MatchEntity saveMatchToDB(MatchScore matchScore) throws MatchNotCreatedException {
        MatchDao matchDao = new MatchDao();

        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setPlayer1(matchScore.getPlayer1());
        matchEntity.setPlayer2(matchScore.getPlayer2());
        matchEntity.setWinner(matchScore.getWinner());

        return matchDao.createMatch(matchEntity);
    }

    // Fetch matches for the given page number with pagination
    public List<MatchEntity> GetMatchesFromDB(int pageNum, int recordsPerPage) {
        MatchDao matchDao = new MatchDao();

        if (pageNum < 1) pageNum = 1;  // Ensure page number is positive

        // Calculate offset based on page number and records per page
        int offset = (pageNum - 1) * recordsPerPage;

        // Fetch matches for the given page from the DAO
        List<MatchEntity> paginatedMatchEntities = matchDao.getMatchesWithPagination(offset, recordsPerPage);

        return paginatedMatchEntities;
    }

    // Get total number of matches for pagination calculation
    public int getTotalMatchCount() {
        MatchDao matchDao = new MatchDao();
        return matchDao.getTotalMatchCount();  // Retrieve the total count from MatchDao
    }
}
