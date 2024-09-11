package main.dao;

import main.customExceptions.MatchNotCreatedException;
import main.customExceptions.MatchNotFoundException;
import main.entities.Match;
import main.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDao {

    private static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static Match createMatch(Match match) throws MatchNotCreatedException {
        Transaction tx = null;

        try(Session session = factory.openSession()) {

            tx = session.beginTransaction();
            Integer generatedId = (Integer) session.save(match);
            match.setId(generatedId);
            tx.commit();

        }catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new MatchNotCreatedException(e.getMessage());
        }

        return match;
    }

    public static Match getMatchById(int id) throws MatchNotFoundException {

        Transaction tx = null;
        Match match = null;

        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            match = session.get(Match.class, id);
            tx.commit();
        }catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new MatchNotFoundException(e.getMessage());
        }
        return match;
    }

    public static Match updateMatch(Match match) {
        return null;
    }

    public static void deleteMatch(int id) throws MatchNotFoundException {

        Match match = MatchDao.getMatchById(id);

        Transaction tx = null;

        try(Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.remove(match);
            tx.commit();
        }catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw new MatchNotFoundException(e.getMessage());
        }
    }

    public static List<Match> getAllMatches() {
        Transaction tx = null;
        List<Match> matches = null;

        try(Session session = factory.openSession()) {

            tx = session.beginTransaction();
            matches = session.createQuery("from Match").list();
            tx.commit();

        }catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return matches;
    }


}
