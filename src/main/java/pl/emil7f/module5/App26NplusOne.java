package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Order;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
Problem n+1
Rozwiązanie nr2 z dodatkowym joinem, ale duplikują się rekordy -> dodać distinct
 */
public class App26NplusOne {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App26NplusOne.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        EntityGraph entityGraph = em.getEntityGraph("order-and-rows");

        Map<String, Object> map = new HashMap<>();
        map.put("javax.persistence.fetchgraph", entityGraph);

        List<Order> products = em.createQuery(
                "select o from Order o",
                Order.class
        )
                .setHint("javax.persistence.fetchgraph", entityGraph)
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
