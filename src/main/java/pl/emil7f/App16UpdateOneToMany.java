package pl.emil7f;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Attribute;
import pl.emil7f.entity.Product;
import pl.emil7f.entity.Review;
import pl.emil7f.entity.ReviewDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * 16. OneToMany update
 * Product - Review
 * getUpdatedReviews - symuluje sytuacje gdzie dostajemy z frontendu wstrzyknięta zależnośc
 */
public class App16UpdateOneToMany {


    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<ReviewDto> reviewDtos = getUpdatedReviews();

        Product product = em.find(Product.class, 3L);
        for (Review review : product.getReviews()) {
            for (ReviewDto reviewDto : reviewDtos) {
                if (review.getId().equals(reviewDto.getId())) {
                    review.setContent(reviewDto.getContent());
                    review.setRating(reviewDto.getRating());
                }
            }
        }

        em.getTransaction().commit();
        em.close();


    }

    private static List<ReviewDto> getUpdatedReviews() {
        List<ReviewDto> list = new ArrayList<>();
        list.add(new ReviewDto(13L, "Treść opinii 3!!!!", 10));
        list.add(new ReviewDto(14L, "Treść opinii 4!!!!", 10));
        list.add(new ReviewDto(15L, "Treść opinii 5!!!!", 10));
        return list;
    }


}
