package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


/*
Problem n+1
Rozwiązanie nr1 z dodatkowym joinem, ale duplikują się rekordy
 */
public class App26NplusOne {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App26NplusOne.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Order> products = em.createQuery(
                "select o from Order o" +
                        " inner join fetch o.orderRows ",
                Order.class)
                .getResultList();

        logger.info("Ilość rekordów: " + products.size());

        for (Order order : products) {
            logger.info(order);
            logger.info(order.getOrderRows());
        }

        em.getTransaction().commit();
        em.close();
    }
}
