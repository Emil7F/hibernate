package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *              select   *   from    product p
 *              inner join    category c     on     p.category_id=c.id
 *              where     c.id=1;
 *
 *     Takie query zadziała, ale po przeanalizowaniu logów można zobaczyć że hibernate wykonuje dwa zapytania do bazy danych
 *     pomimo tego że zastosowalismy inner join'a
 *     dzieje się tak ze wzgledu na fakt ze hibernate pobiera powiązanie tabele leniwie
 *
 *     Rozwiązaniem jest wstawienie słowa kluczowego FETCH - które sprawi że wszystko pobierze się jednym zapytaniem
 *
 */

public class App18Join {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App18Join.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Product> query = em.createQuery("select p from Product p " +
                "inner join fetch p.category c " +
                "where c.id=:id", Product.class);

        query.setParameter("id", 1L);
        List<Product> resultList = query.getResultList();

        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getCategory());
        }


        em.getTransaction().commit();
        em.close();
    }
}
