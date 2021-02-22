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
 * UWAGA! SUBSELECT działa w bardzo specyficzny sposób, w przypadku gdy chcemy pogrupować wiersze i wybrać tylko 5
 *
 Hibernate:
 select
 order0_.id as id1_3_,
 order0_.created as created2_3_,
 order0_.total as total3_3_
 from
 `
 order` order0_ order by
 order0_.created desc limit ?              ///  jest zapytanie pobierające zamówienia i jest tutaj fraza limit
 2021-02-22 19:22:58.573 INFO  [main] App23FetchMode - Order{id=1, created=2020-10-10T14:00, total=59.97}
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
 order` order0_                         // w zapytaniu po wiersze już nie ma 'limit' -> czyli pobierze dwie tabele w całości
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
                "select o from Order o " +
                        " order by o.created desc",
                Order.class)
                .setMaxResults(5)
                .getResultList();

        for (Order order : orders) {
            logger.info(order);
            logger.info(order.getOrderRows());
        }

        em.getTransaction().commit();
        em.close();

    }
}
