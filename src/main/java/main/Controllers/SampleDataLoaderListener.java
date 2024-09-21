package main.Controllers;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import main.customExceptions.MatchNotCreatedException;
import main.customExceptions.PlayerNotCreatedException;
import main.dao.MatchDao;
import main.dao.PlayerDao;
import main.entities.MatchEntity;
import main.entities.PlayerEntity;

public class SampleDataLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        PlayerDao playerDao = new PlayerDao();
        MatchDao matchDao = new MatchDao();

        PlayerEntity alice = new PlayerEntity("Alice");
        PlayerEntity bob = new PlayerEntity("Bob");
        PlayerEntity charlie = new PlayerEntity("Charlie");
        PlayerEntity dana = new PlayerEntity("Dana");
        PlayerEntity eve = new PlayerEntity("Eve");
        PlayerEntity abobus = new PlayerEntity("Abobus");
        PlayerEntity Karim = new PlayerEntity("Karim");

        try{
            playerDao.savePlayer(alice);
            playerDao.savePlayer(bob);
            playerDao.savePlayer(charlie);
            playerDao.savePlayer(dana);
            playerDao.savePlayer(eve);
            playerDao.savePlayer(abobus);
            playerDao.savePlayer(Karim);
        } catch (PlayerNotCreatedException e) {
            throw new RuntimeException(e);
        }

        MatchEntity matchEntity1 = new MatchEntity(alice, bob, alice);    // Alice wins against Bob
        MatchEntity matchEntity2 = new MatchEntity(charlie, dana, dana);  // Dana wins against Charlie
        MatchEntity matchEntity3 = new MatchEntity(bob, charlie, charlie); // Charlie wins against Bob
        MatchEntity matchEntity4 = new MatchEntity(dana, eve, eve);       // Eve wins against Dana
        MatchEntity matchEntity5 = new MatchEntity(alice, charlie, alice); // Alice wins against Charlie
        MatchEntity matchEntity6 = new MatchEntity(bob, Karim, Karim);
        MatchEntity matchEntity7 = new MatchEntity(Karim, abobus, abobus);

        try{
            matchDao.createMatch(matchEntity1);
            matchDao.createMatch(matchEntity2);
            matchDao.createMatch(matchEntity3);
            matchDao.createMatch(matchEntity4);
            matchDao.createMatch(matchEntity5);
            matchDao.createMatch(matchEntity6);
            matchDao.createMatch(matchEntity7);
        } catch (MatchNotCreatedException e) {
            throw new RuntimeException(e);
        }
    }


}
