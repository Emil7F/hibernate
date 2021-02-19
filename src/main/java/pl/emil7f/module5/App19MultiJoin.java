package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Category;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *  Exception:
 *      org.hibernate.loader.MultipleBagFetchException
 *      błąd oznacza to że hibernate nie może pobrać powiązanych kolekcji. W tym przypadku produktów i opinii o tych produktach
 *      Problem lezy w implementacji listy, hibernate ma swoją implementacje listy o nazwie:
 *      PersistentBag
 *      Rozwiązań tego problemu jest kilka, najprostsze to zamiana listy na sety
 *
 */

public class App19MultiJoin {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App19MultiJoin.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Category> query = em.createQuery(
                "select c from Category c "
                        + " left join fetch c.product p "
                        + " left join fetch p.reviews "
                        + " where c.id=:id ",
                Category.class
        );
        query.setParameter("id", 1L);

        List<Category> resultList = query.getResultList();
        for (Category c : resultList) {
            logger.info(c);
            logger.info(c.getProduct());
            for (Product product : c.getProduct()) {
                logger.info(product.getReviews());
            }


        }

        em.getTransaction().commit();
        em.close();
    }
}
