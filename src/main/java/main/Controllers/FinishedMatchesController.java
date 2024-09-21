package main.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.dao.MatchDao;
import main.entities.MatchEntity;
import main.service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class FinishedMatchesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MatchDao matchDao = new MatchDao();
        FinishedMatchesPersistenceService finishedMatchesService = new FinishedMatchesPersistenceService();

        List<MatchEntity> allMatchEntities;
        String playerName = req.getParameter("filter_by_player_name");
        int page = 1;
        int recordsPerPage = 5;
        int totalRecords;
        int totalPages;

        if (req.getParameter("page") != null) {
            try {
                int pageNum = Integer.parseInt(req.getParameter("page"));
                if (pageNum > 0) {
                    page = pageNum;
                }
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        if (playerName != null && !playerName.isEmpty()) {
            allMatchEntities = matchDao.getMatchesByPlayerName(playerName);
            totalRecords = allMatchEntities.size();
        } else {
            allMatchEntities = finishedMatchesService.GetMatchesFromDB(page, recordsPerPage);
            totalRecords = finishedMatchesService.getTotalMatchCount();
        }

        totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        req.setAttribute("matchEntities", allMatchEntities);
        req.setAttribute("page", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("FinishedMatchesPage.jsp").forward(req, resp);

    }
}
