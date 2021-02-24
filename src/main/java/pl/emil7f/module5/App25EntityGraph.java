package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Order;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
NamedGraph
 Jeśli nie podamy mapy jako trzeci parametr properties to w zapytaniu hibernate w drugim zapytaniu dociągnie leniwie OrderRows

Hibernate:
    select
        order0_.id as id1_3_0_,
        order0_.created as created2_3_0_,
        order0_.total as total3_3_0_,
        orderrows1_.order_id as order_id4_4_1_,
        orderrows1_.id as id1_4_1_,
        orderrows1_.id as id1_4_2_,
        orderrows1_.price as price2_4_2_,
        orderrows1_.product_id as product_3_4_2_
    from
        `
    order` order0_ left outer join
        order_row orderrows1_
            on order0_.id=orderrows1_.order_id
    where
        order0_.id=?
2021-02-24 18:38:34.082 INFO  [main] App25EntityGraph - Order{id=1, created=2020-10-10T14:00, total=59.97}
2021-02-24 18:38:34.082 INFO  [main] App25EntityGraph - [OrderRow{id=3, price=19.99}, OrderRow{id=2, price=19.99}, OrderRow{id=1, price=19.99}]


 */

public class  App25EntityGraph {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App25EntityGraph.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        EntityGraph entityGraph = em.getEntityGraph("order-rows");

        Map<String, Object> map = new HashMap<>();

        map.put("javax.persistence.fetchgraph", entityGraph);

        Order order = em.find(Order.class, 1L, map);

        logger.info(order);
        logger.info(order.getOrderRows());

        em.getTransaction().commit();
        em.close();

    }
}
