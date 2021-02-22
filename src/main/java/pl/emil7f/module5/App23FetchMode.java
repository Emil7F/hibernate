package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *   Pobieramy Order za pomocą find, a OrderRow dociąga się leniwie (FetchType.LAZY)
 *   FetchMode.SELECT - domyślny
 *   FetchMode.JOIN - w zapytaniu zostanie zastosowany dodatkowy left outer join
 *   FetchMode.SUBSELECT - w stosunku do SELECT zapytanie się nie zmienia w tym przypadku
 *   dzieje się tak dlatego że opcja SUBSELECT generuje dodatkowe podzapytanie dopiero w sytuacji gdy pobieramy listę osobnym zapytaniem select
 *   a nie poprzez metode find, w metodzie find nie daje to żadnego efektu
 *
 Hibernate:
 select
 order0_.id as id1_3_0_,
 order0_.created as created2_3_0_,
 order0_.total as total3_3_0_
 from
 `
 order` order0_ where
 order0_.id=?
 2021-02-22 18:58:21.769 INFO  [main] App23FetchMode - Order{id=1, created=2020-10-10T14:00, total=59.97}

 Hibernate:
 select
 orderrows0_.order_id as order_id4_4_0_,
 orderrows0_.id as id1_4_0_,
 orderrows0_.id as id1_4_1_,
 orderrows0_.price as price2_4_1_,
 orderrows0_.product_id as product_3_4_1_
 from
 order_row orderrows0_
 where
 orderrows0_.order_id=?
 2021-02-22 18:58:21.785 INFO  [main] App23FetchMode - [OrderRow{id=3, price=19.99}, OrderRow{id=2, price=19.99}, OrderRow{id=1, price=19.99}]


 *
 */

public class App23FetchMode {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App23FetchMode.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Order order = em.find(Order.class, 1L);
        logger.info(order);
        logger.info(order.getOrderRows());

        em.getTransaction().commit();
        em.close();

    }
}
