package main.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.customExceptions.PlayerNotCreatedException;
import main.dao.PlayerDao;
import main.entities.MatchEntity;
import main.service.OngoingMatchesService;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;

    @Override
    public void init() throws ServletException {
        this.ongoingMatchesService = new OngoingMatchesService(new PlayerDao());
    }

    private static final String NEW_MATCH_PAGE = "NewMatchPage.jsp";
    private static final String ERROR_PAGE = "ErrorPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(NEW_MATCH_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String player1Name = req.getParameter("player1Name");
        String player2Name = req.getParameter("player2Name");

        if (player1Name == null || player2Name == null || player1Name.isBlank() || player2Name.isBlank()) {
            req.setAttribute("errorMessage", "Please fill all the required fields");
            req.setAttribute("player1NameValue", player1Name);
            req.setAttribute("player2NameValue", player2Name);
            req.getRequestDispatcher(NEW_MATCH_PAGE).forward(req, resp);

        }else {
            try{
                //find/create players, create match and put to collection
                MatchEntity match = ongoingMatchesService.createMatch(player1Name, player2Name);

                //go to ongoing matchPage
                resp.sendRedirect("/match-score?uuid=" + match.getMatchId());

            } catch (PlayerNotCreatedException e) {
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
            }
        }
    }


}
