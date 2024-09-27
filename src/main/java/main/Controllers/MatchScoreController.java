package main.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.customExceptions.MatchNotCreatedException;
import main.dao.MatchDao;
import main.dao.PlayerDao;
import main.dto.finalScoreDto;
import main.dto.matchScoreDto;
import main.entities.MatchEntity;
import main.service.FinishedMatchesPersistenceService;
import main.service.MatchScoreCalculationService;
import main.service.OngoingMatchesService;
import main.utils.MatchScoreMapper;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    MatchScoreMapper matchMapper = MatchScoreMapper.INSTANCE;

    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService scoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesService;

    @Override
    public void init() {
        this.finishedMatchesService = new FinishedMatchesPersistenceService(new MatchDao());
        this.ongoingMatchesService = new OngoingMatchesService(new PlayerDao());
        this.scoreCalculationService = new MatchScoreCalculationService();
    }

    private static final String ERROR_PAGE = "ErrorPage.jsp";
    private static final String FINAL_SCORE_PAGE = "FinalScorePage.jsp";
    private static final String ONGOING_MATCH_PAGE = "OngoingMatchPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get match by id
        MatchEntity matchScore = ongoingMatchesService.getMatchById(UUID.fromString(req.getParameter("uuid")));

        //if winner is assigned then proceed to final score page
        if (matchScore.isWinnerAssigned()) {
            try {
                //saving to db
                finishedMatchesService.saveMatchToDB(matchScore);
                // and deleting from collection
                ongoingMatchesService.removeMatchFromCollection(matchScore.getMatchId());
            } catch (MatchNotCreatedException e) {
                //rendering error page
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
            }

            finalScoreDto finalScoreDto = matchMapper.MatchEntityToFinalScoreDto(matchScore);
            req.setAttribute("finalScoreDto", finalScoreDto);

            //rendering final score page
            req.getRequestDispatcher(FINAL_SCORE_PAGE).forward(req, resp);
        } else {

            matchScoreDto matchScoreDto = matchMapper.matchEntityToMatchScoreDto(matchScore);
            req.setAttribute("matchScoreDto", matchScoreDto);

            //rendering ongoing match page
            req.getRequestDispatcher(ONGOING_MATCH_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        String winnerId = req.getParameter("winner");

        //get outDated match
        MatchEntity ongoingMatch = ongoingMatchesService.getMatchById(uuid);

        //calculate the points
        scoreCalculationService.calculateMatch(ongoingMatch, winnerId);

        //update the match in the collection
        ongoingMatchesService.update(ongoingMatch);

        resp.sendRedirect("/match-score?uuid=" + uuid);

    }
}
