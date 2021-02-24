package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Order;
import pl.emil7f.entity.OrderRow;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
NamedGraph

 fetchgraph - to sprawia on że tylko właściwości zdefiniowane w grafie są pobierane natychmiast - czyli EAGER
 cała reszta będzie pobierana LAZY

 loadgraph - właściwości w grafie będą pobierane natychmiast - EAGER, a wszystkie inne zdefiniowane w encji będą zachowywały
 swój domyślny fetchType
 */

public class  App25EntityGraph {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App25EntityGraph.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        EntityGraph entityGraph = em.getEntityGraph("order-rows");

        Map<String, Object> map = new HashMap<>();

        map.put("javax.persistence.loadgraph", entityGraph);

        Order order = em.find(Order.class, 1L, map);

        logger.info(order);
        for(OrderRow orderRow : order.getOrderRows()){
            logger.info(orderRow);
            logger.info(orderRow.getProduct());
        }




        em.getTransaction().commit();
        em.close();

    }
}
