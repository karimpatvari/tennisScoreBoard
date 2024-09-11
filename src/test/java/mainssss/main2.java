package mainssss;

import main.entities.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class main2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Player.class)
                .buildSessionFactory();


        try{
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Player player1 = session.get(Player.class, 1);

            System.out.println(player1);

            session.getTransaction().commit();
        }finally {
            factory.close();
        }



    }
}
