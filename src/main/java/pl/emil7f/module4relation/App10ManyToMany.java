package pl.emil7f.module4relation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Attribute;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 10. ManyToMany
 */
public class App10ManyToMany {


    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 3L);
        logger.info(product);
        logger.info(product.getAttributes());

        logger.info("\n======================\n");

        Attribute attribute = em.find(Attribute.class, 1L);
        logger.info(attribute);
        logger.info(attribute.getProducts());

        em.getTransaction().commit();
        em.close();


    }


}
