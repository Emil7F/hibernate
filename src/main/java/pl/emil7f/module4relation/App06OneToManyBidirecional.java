package pl.emil7f.module4relation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * 6. OneToMany bidirectional, w obu klasach (Product i Review) ustawiamy adnotacje nad polami
 */
public class App06OneToManyBidirecional {


    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Review> reviews = em.createQuery("select r from Review r").getResultList();
        for (Review review : reviews) {
            logger.info(review);
            logger.info(review.getProduct());
        }

        em.getTransaction().commit();
        em.close();


    }


}
