package pl.emil7f.module4relation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 9. OneToOne Bidirecional
 * W tym przypadku stosując relacje OneToOne, symulujemy sytuacje gdzie jeden produkt ma jedną kategorie..
 * W rzeczywistości jest inaczej, ale to jest na potrzeby przykładu
 */
public class App09OneToOneBidirectional {


    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Category category = em.find(Category.class, 1L);
        logger.info(category);
        logger.info(category.getProduct());

        em.getTransaction().commit();
        em.close();


    }


}
