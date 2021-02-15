package pl.emil7f;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Attribute;
import pl.emil7f.entity.Product;
import pl.emil7f.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 13. OneToMany Add
 */
public class App13AddOneToMany {


    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 5L);
        Review review = new Review();
        review.setContent("Nowa Opinia ");
        review.setRating(5);
        product.addReview(review);

        em.getTransaction().commit();
        em.close();


    }


}