package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Produkt kartezjański to produkt który powstaje w wyniku mnożenia dwóch zbiorów
 * Jeśli pobieramy dane z bazy danych z pomocą join'a to dane pobierane są w taki sposób
 * <p>
 * // Wielkość listy wynosi: 125
 * // Jest 5 produktów, które mają po 5 atrybutów i kazdy produkt ma 5 opinii
 * // Mozna to rozwiązać za pomocą słowa distinct oraz skrócić zapytanie żeby dodatkowe tabele pobierały się leniwie
 * // z tym że pojawiają się nam dodatkowe zapytania, czyli problem n+1
 * <p>
 * W poniższy sposób hibernate pobierze produkty wraz z atrybutami za pierszym razem ( w jednej sesji )
 * a w drugim zapytaniu pobierze produkty oraz opinie i połączy nam wyniki w całość
 * (intelliJ wyszarza część kodu ale w tym momencie to tak nie działa.
 * <p>
 * W zapytaniu słowo kluczowe distinct ogranicza wyniki do unikalnej liczby obiektów. W hibernate sie przydaje ale w SQL już nie
 * Można podpowiedzieć hibernatowi żeby nie przekazywał tego słowa kluczowego do DB  i robimy to poprzez metode .setHint(QueryHints...)
 */
public class App20CartesianProduct {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App20CartesianProduct.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Product> resultList = em.createQuery(
                "select distinct p from Product p " +
                        " left join fetch p.attributes ",
                Product.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();

        resultList = em.createQuery(
                "select distinct p from Product p " +
                        " left join fetch p.reviews ",
                Product.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();


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
