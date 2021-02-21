package pl.emil7f.module5;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Stworzenie nowych tabel oraz aktualizacja danych
 */

public class App21MultiMultiJoin {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App21MultiMultiJoin.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        /*
        select cu.id, cu.lastname customer, ca.name category, SUM(orw.price) total  from customer cu
        inner join `order` o on cu.id=o.customer_id
        inner join order_row orw on o.id=orw.order_id
        inner join product p on orw.product_id=p.id
        inner join category ca on p.category_id=ca.id
        group by ca.id, cu.id
        having total > 50
        order by total desc
        ;
         */

        Query query = em.createQuery(
                " select c from Customer c " +
                        "inner join fetch c.orders o " +
                        "inner join fetch o.orderRows orw " +
                        "inner join fetch orw.product p " +
                        "inner join fetch p.category ca "
        );


        em.getTransaction().commit();
        em.close();
    }
}
