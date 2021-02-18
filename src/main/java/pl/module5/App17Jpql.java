package pl.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;

import javax.persistence.*;
import java.util.List;

/**
 * Proste zapytanie JPQL
 */

public class App17Jpql {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App17Jpql.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(
                "select count(p)  from Product p  group by p.category"
        );


        List<Long> resultList = query.getResultList();
        for (Long aLong:resultList) {
            logger.info(aLong);
        }



        em.getTransaction().commit();
        em.close();


    }

}
