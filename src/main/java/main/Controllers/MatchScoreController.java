package main.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.customExceptions.PlayerNotFoundException;
import main.dao.PlayerDao;
import main.entities.MatchScore;
import main.entities.Player;
import main.service.MatchScoreCalculationService;
import main.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MatchScore matchScore = OngoingMatchesService.getMatchById(UUID.fromString(req.getParameter("uuid")));
        Player player1ById = null;
        Player player2ById = null;
        try {
            player1ById = PlayerDao.getPlayerById(matchScore.getPlayer1ID());
        } catch (PlayerNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            player2ById = PlayerDao.getPlayerById(matchScore.getPlayer2ID());
        } catch (PlayerNotFoundException e) {
            throw new RuntimeException(e);
        }


        req.setAttribute("player1Name", player1ById.getName());
        req.setAttribute("player1Sets", matchScore.getPlayer1Sets());
        req.setAttribute("player1Games", matchScore.getPlayer1Games());
        req.setAttribute("player1Points", matchScore.getPlayer1Points());
        req.setAttribute("player2Name", player2ById.getName());
        req.setAttribute("player2Sets", matchScore.getPlayer2Sets());
        req.setAttribute("player2Games", matchScore.getPlayer2Games());
        req.setAttribute("player2Points", matchScore.getPlayer2Points());

        req.setAttribute("matchId", matchScore.getMatchId());

        req.getRequestDispatcher("MatchScorePage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));


        String player2winsPoint = req.getParameter("player2point");
        String player1winsPoint = req.getParameter("player1point");

        MatchScore match = OngoingMatchesService.getMatchById(uuid);
        match = MatchScoreCalculationService.calculateTheScore(match, player1winsPoint, player2winsPoint);
        OngoingMatchesService.updateScore(match);

        resp.sendRedirect("/match-score?uuid="+uuid.toString());

    }
}
