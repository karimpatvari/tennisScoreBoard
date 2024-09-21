package main.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.customExceptions.PlayerNotCreatedException;
import main.dao.PlayerDao;
import main.entities.MatchScore;
import main.entities.PlayerEntity;
import main.service.OngoingMatchesService;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("NewMatchPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String player1Name = req.getParameter("player1Name");
        String player2Name = req.getParameter("player2Name");

        if (player1Name == null || player2Name == null || player1Name.isBlank() || player2Name.isBlank()) {
            req.setAttribute("errorMessage", "Please fill all the required fields");
            req.setAttribute("player1NameValue", player1Name);
            req.setAttribute("player2NameValue", player2Name);
            req.getRequestDispatcher("NewMatchPage.jsp").forward(req, resp);

        }else {
            try{
                PlayerDao playerDao = new PlayerDao();
                PlayerEntity player1 = playerDao.getOrSavePlayer(player1Name);
                PlayerEntity player2 = playerDao.getOrSavePlayer(player2Name);

                OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
                MatchScore match = ongoingMatchesService.createMatch(player1,player2);

                String redirectUrl = "/match-score?uuid=" + match.getMatchId();
                resp.sendRedirect(redirectUrl);

            } catch (PlayerNotCreatedException e) {
                req.setAttribute("errorMessage", "Some error occurred, Try again");
                req.getRequestDispatcher("NewMatchPage.jsp").forward(req, resp);
            }
        }
    }


}
