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
 * FetchMode.SUBSELECT - pojawiło się jedno dodatkowe podzapytanie w klauzuli in
 * UWAGA! SUBSELECT działa w bardzo specyficzny sposób
 *
 Hibernate:
 select
 orderrows0_.order_id as order_id4_4_1_,
 orderrows0_.id as id1_4_1_,
 orderrows0_.id as id1_4_0_,
 orderrows0_.price as price2_4_0_,
 orderrows0_.product_id as product_3_4_0_
 from
 order_row orderrows0_
 where
 orderrows0_.order_id in (
 select
 order0_.id
 from
 `
 order` order0_
 )

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
