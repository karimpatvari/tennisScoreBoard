package mainssss;

import main.entities.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class main {
    public static void main(String[] args) {

        Player player = new Player("Frederick");

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Player.class)
                .buildSessionFactory();

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(player);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }

        System.out.println("ok");
    }
}
