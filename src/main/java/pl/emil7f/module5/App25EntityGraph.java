package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Order;
import pl.emil7f.entity.OrderRow;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NamedGraph
 * Alternatywny sposób tworzenia grafów, bardziej dynamiczny
 */

public class App25EntityGraph {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App25EntityGraph.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        EntityGraph entityGraph = em.createEntityGraph(Order.class);
        entityGraph.addAttributeNodes("orderRows");
        entityGraph.addAttributeNodes("customer");
        Subgraph<OrderRow> orderRows = entityGraph.addSubgraph("orderRows");
        orderRows.addAttributeNodes("product");

        List<Order> orders = em.createQuery(
                "select o from Order o ",
                Order.class)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();

        for (Order order : orders) {
            logger.info(order);
            for (OrderRow orderRow : order.getOrderRows()) {
                logger.info(orderRow);
                logger.info(orderRow.getProduct());
            }
        }


        em.getTransaction().commit();
        em.close();

    }
}
