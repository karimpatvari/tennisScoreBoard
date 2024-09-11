package mainssss;

import main.entities.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class main3 {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        try{
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Player player1 = new Player("Abobik");


            session.getTransaction().commit();
        }finally {
            factory.close();
        }

    }
}
