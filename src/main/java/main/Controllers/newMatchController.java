package main.Controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.customExceptions.PlayerNotCreatedException;
import main.dao.PlayerDao;
import main.entities.MatchScore;
import main.entities.Player;
import main.service.OngoingMatchesService;

import java.io.IOException;

@WebServlet("/new-match")
public class newMatchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("NewMatchPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Player player1 = new Player(req.getParameter("player1Name"));
        Player player2 = new Player(req.getParameter("player2Name"));

        try {
            player1 = PlayerDao.getOrSavePlayer(player1);
            player2 = PlayerDao.getOrSavePlayer(player2);

        } catch (PlayerNotCreatedException | RuntimeException e) {
            throw new RuntimeException(e);
        }

        MatchScore match = OngoingMatchesService.createMatch(player1.getId(), player2.getId());
        resp.sendRedirect("/match-score?uuid="+match.getMatchId());
    }
}
