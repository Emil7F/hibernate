package pl.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Proste zapytanie JPQL
 * pobieranie produktu z parametrem
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

        query.setParameter("id",3L);

        List<Product> resultList = query.getResultList();

        for (Product product : resultList) {
            logger.info(product);
        }

        em.getTransaction().commit();
        em.close();
    }

}