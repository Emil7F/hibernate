package pl.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;

import javax.persistence.*;
import java.util.List;

/**
 * Proste zapytanie JPQL
 * pobieranie produktu z parametrem z zabezpieczeniem w NoResultException
 * za pomocą metody getResultStream i obsługą Optionala
 */

public class App17Jpql {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App17Jpql.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Product> query = em.createQuery(
                "select p from Product p where p.id=:id",
                Product.class
        );

        query.setParameter("id", 10000L);


        try {
            Product singleResult = query.getResultStream()
                    .findFirst()
                    .orElseThrow(() -> new NoResultException());
            logger.info(singleResult);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            logger.error("Brak wyników........", e);
        } finally {
            em.close();
        }


    }

}
