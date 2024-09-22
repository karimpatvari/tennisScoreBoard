package main.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.customExceptions.MatchNotCreatedException;
import main.entities.MatchScore;
import main.service.FinishedMatchesPersistenceService;
import main.service.MatchScoreCalculationService;
import main.service.OngoingMatchesService;


import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService scoreCalculationService;

    @Override
    public void init() {
        this.ongoingMatchesService = new OngoingMatchesService(new FinishedMatchesPersistenceService());
        this.scoreCalculationService = new MatchScoreCalculationService();
    }

    private static final String ERROR_PAGE = "ErrorPage.jsp";
    private static final String FINAL_SCORE_PAGE = "FinalScorePage.jsp";
    private static final String ONGOING_MATCH_PAGE = "OngoingMatchPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get match by id
        MatchScore matchScore = ongoingMatchesService.getMatchById(UUID.fromString(req.getParameter("uuid")));

        //sending matchScore to jsp page, because it will not be changed
        req.setAttribute("matchScore", matchScore);

        //if winner is assigned then proceed to final score page
        if (matchScore.isWinnerAssigned()) {
            try {
                //saving to db and deleting from collection
                ongoingMatchesService.completeMatch(matchScore);
            } catch (MatchNotCreatedException e) {
                //rendering error page
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
            }

            //rendering final score page
            req.getRequestDispatcher(FINAL_SCORE_PAGE).forward(req, resp);
        } else {
            //rendering ongoing match page
            req.getRequestDispatcher(ONGOING_MATCH_PAGE).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        String winnerId = req.getParameter("winner");

        //get outDated match
        MatchScore ongoingMatch = ongoingMatchesService.getMatchById(uuid);

        //calculate the points
        scoreCalculationService.calculateMatch(ongoingMatch, winnerId);

        //update the match in the collection
        ongoingMatchesService.updateScore(ongoingMatch);

        resp.sendRedirect("/match-score?uuid=" + uuid);

    }
}
