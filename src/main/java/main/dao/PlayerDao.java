package main.dao;

import main.customExceptions.PlayerNotCreatedException;
import main.customExceptions.PlayerNotFoundException;
import main.entities.PlayerEntity;
import main.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PlayerDao {

    public PlayerDao() {
    }

    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public PlayerEntity savePlayer(PlayerEntity playerEntity) throws PlayerNotCreatedException {

        Transaction tx = null;

        try (Session session = factory.getCurrentSession()) {
            tx = session.beginTransaction();

            Integer generatedId = (Integer) session.save(playerEntity);
            playerEntity.setId(generatedId);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }

        if (playerEntity.getId() == null) {
            throw new PlayerNotCreatedException();
        }

        return playerEntity;
    }

    public PlayerEntity getPlayerByName(String name) throws PlayerNotFoundException {

        Transaction tx = null;
        PlayerEntity player;

        try (Session session = factory.getCurrentSession()) {
            tx = session.beginTransaction();

            player = session.createQuery("from PlayerEntity where name = :name", PlayerEntity.class).setParameter("name", name).uniqueResult();

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }

        if (player == null) {
            throw new PlayerNotFoundException();
        }

        return player;
    }

    public PlayerEntity getOrSavePlayer(String playerName) throws PlayerNotCreatedException {
        PlayerEntity PlayerEntity = new PlayerEntity();
        PlayerEntity.setName(playerName);

        try {
            PlayerEntity = getPlayerByName(playerName);
        } catch (PlayerNotFoundException e) {
            PlayerEntity = savePlayer(PlayerEntity);
        }

        return PlayerEntity;
    }

}
