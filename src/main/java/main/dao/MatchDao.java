package main.dao;

import main.customExceptions.MatchNotCreatedException;
import main.customExceptions.PlayerNotFoundException;
import main.entities.MatchEntity;
import main.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDao {

    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public MatchEntity createMatch(MatchEntity matchEntity) throws MatchNotCreatedException {
        Transaction tx = null;

        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();

            Integer generatedId = (Integer) session.save(matchEntity);
            matchEntity.setId(generatedId);

            tx.commit();
        }catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        }

        if (matchEntity.getId() == null) {
            throw new MatchNotCreatedException();
        }

        return matchEntity;
    }

    public List<MatchEntity> getMatchesWithPagination(int offset, int limit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<MatchEntity> matchEntities = null;

        try {
            matchEntities = session.createQuery("FROM MatchEntity", MatchEntity.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return matchEntities;
    }

    public int getTotalMatchCount() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long count = 0L;

        try {
            count = (Long) session.createQuery("SELECT COUNT(m) FROM MatchEntity m").uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return count.intValue();
    }

    public List<MatchEntity> getMatchesByPlayerName(String playerName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<MatchEntity> matchEntities = null;

        try {
            matchEntities = session.createQuery("FROM MatchEntity m WHERE m.player1.name = :playerName OR m.player2.name = :playerName", MatchEntity.class)
                    .setParameter("playerName", playerName)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            session.close();
        }

        return matchEntities;
    }

}
