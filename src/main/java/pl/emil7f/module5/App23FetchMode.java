package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Pobieramy Order za pomocą query , a OrderRow dociąga się leniwie (FetchType.LAZY)
 * FetchMode.SELECT - domyślny
 * FetchMode.JOIN - nie wygenerował się Join, tylko kolejne zapytania po nowe wiersze zamówienia (tj. w trybie EAGER)


 */

public class App23FetchMode {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App23FetchMode.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        // Order order = em.find(Order.class, 1L);
        List<Order> orders = em.createQuery(
                "select o from Order o ",
                Order.class)
                .getResultList();

        for (Order order : orders) {
            logger.info(order);
            logger.info(order.getOrderRows());
        }

        em.getTransaction().commit();
        em.close();

    }
}
