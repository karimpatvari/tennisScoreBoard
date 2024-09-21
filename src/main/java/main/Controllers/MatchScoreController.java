package main.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.customExceptions.MatchNotCreatedException;
import main.customExceptions.PlayerNotFoundException;
import main.dao.PlayerDao;
import main.entities.MatchScore;
import main.entities.PlayerEntity;
import main.service.FinishedMatchesPersistenceService;
import main.service.MatchScoreCalculationService;
import main.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
        FinishedMatchesPersistenceService finishedMatchesService = new FinishedMatchesPersistenceService();

        UUID id = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = ongoingMatchesService.getMatchById(id);

        PlayerEntity player1ById = matchScore.getPlayer1();
        PlayerEntity player2ById = matchScore.getPlayer2();

        //if winner is assigned
        if (matchScore.isWinnerAssigned()) {

            PlayerEntity winner = matchScore.getWinner();

            try {
                finishedMatchesService.saveMatchToDB(player1ById, player2ById, winner);
            } catch (MatchNotCreatedException e) {
                throw new RuntimeException(e);
            }

            req.setAttribute("matchScore", matchScore);
            req.getRequestDispatcher("FinalScorePage.jsp").forward(req, resp);

            ongoingMatchesService.removeMatchFromCollection(matchScore.getMatchId());
            return;
        }


        req.setAttribute("matchScore", matchScore);
        req.getRequestDispatcher("OngoingMatchPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
        MatchScoreCalculationService ScoreCalculationService = new MatchScoreCalculationService();

        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        String winnerId = req.getParameter("winner");

        //get outDated match
        MatchScore ongoingMatch = ongoingMatchesService.getMatchById(uuid);

        //calculate the points
        ScoreCalculationService.calculateMatch(ongoingMatch, winnerId);

        //update the match in the collection
        ongoingMatchesService.updateScore(ongoingMatch);

        resp.sendRedirect("/match-score?uuid=" + uuid.toString());

    }
}
