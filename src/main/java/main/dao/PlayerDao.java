package main.dao;

import jakarta.persistence.Id;
import main.customExceptions.PlayerNotCreatedException;
import main.customExceptions.PlayerNotDeletedException;
import main.customExceptions.PlayerNotFoundException;
import main.entities.Player;
import main.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PlayerDao {

    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static Player savePlayer(Player player) throws PlayerNotCreatedException {

        Transaction tx = null;

        try (Session session = factory.getCurrentSession()) {
            tx = session.beginTransaction();

            Integer generatedId = (Integer) session.save(player);
            player.setId(generatedId);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }

        if(player.getId() == null){
            throw new PlayerNotCreatedException();
        }

        return player;
    }

    public static Player getPlayerByName(String name) throws PlayerNotFoundException {

        Transaction tx = null;
        Player player = null;

        try (Session session = factory.getCurrentSession()) {
            tx = session.beginTransaction();

            player = session.createQuery(
                            "from Player where name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }

        if (player == null) {
            throw new PlayerNotFoundException("Player with name " + name + " not found");
        }

        return player;
    }

    public static Player getPlayerById(int id) throws PlayerNotFoundException {

        Transaction tx = null;
        Player player = null;

        try (Session session = factory.getCurrentSession()) {

            tx = session.beginTransaction();

            player = session.get(Player.class, id);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }

        if (player == null) {
            throw new PlayerNotFoundException("Player with ID: " + id + " not found");
        }

        return player;
    }

    public static Player updatePlayer(Player updatedPlayer) {
        return null;
    }

    public static void deletePlayer(Integer Id) throws PlayerNotFoundException, PlayerNotDeletedException {

        Player player = PlayerDao.getPlayerById(Id);

        Transaction tx = null;

        try (Session session = factory.getCurrentSession()) {

            tx = session.beginTransaction();
            session.remove(player);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new PlayerNotDeletedException("Failed to delete player " + player, e);
        }

    }

    public static void deletePlayer(String name) throws PlayerNotFoundException, PlayerNotDeletedException {

        Player player = PlayerDao.getPlayerByName(name);

        Transaction tx = null;

        try (Session session = factory.getCurrentSession()) {

            tx = session.beginTransaction();
            session.remove(player);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new PlayerNotDeletedException("Failed to delete player " + player, e);
        }
    }

    public static List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();

        try (Session session = factory.getCurrentSession()) {
            players = session.createQuery("from Player", Player.class).list();

        } catch (Exception e) {
            throw e;
        }
        return players;
    }

    public static Player getOrSavePlayer(Player player) throws PlayerNotCreatedException {
        Player newPlayer = new Player();
        newPlayer.setName(player.getName());

        try {
            newPlayer = PlayerDao.getPlayerByName(player.getName());
        } catch (PlayerNotFoundException e) {
            newPlayer = PlayerDao.savePlayer(player);
        }
        return newPlayer;
    }

}
