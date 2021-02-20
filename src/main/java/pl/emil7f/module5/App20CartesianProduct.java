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
 * Produkt kartezjański to produkt który powstaje w wyniku mnożenia dwóch zbiorów
 * Jeśli pobieramy dane z bazy danych z pomocą join'a to dane pobierane są w taki sposób
 *
 * Wielkość listy wynosi: 125
 * Jest 5 produktów, które mają po 5 atrybutów i kazdy produkt ma 5 opinii
 *  Mozna to rozwiązać za pomocą słowa distinct oraz skrócić zapytanie żeby dodatkowe tabele pobierały się leniwie
 *  z tym że pojawiają się nam dodatkowe zapytania, czyli problem n+1
 */
public class App20CartesianProduct {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App20CartesianProduct.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Product> query = em.createQuery(
                "select distinct p from Product p " +
                        " left join fetch p.attributes ",
                Product.class);

        List<Product> resultList = query.getResultList();
        logger.info("Size: " + resultList.size());
        for (Product product : resultList) {
            logger.info(product);
            logger.info(product.getAttributes());
            logger.info(product.getReviews());
        }
        em.getTransaction().commit();
        em.close();

    }
}
