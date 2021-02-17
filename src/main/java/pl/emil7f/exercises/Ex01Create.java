package pl.emil7f.exercises;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;
import pl.emil7f.entity.ProductType;
import pl.emil7f.module4relation.Runner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ex01Create {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = new Product();
        product.setName("Produkt To delete");
        product.setDescription("Opis Ex to delete");
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        product.setPrice(new BigDecimal("39.00"));
        product.setProductType(ProductType.VIRTUAL);

        em.persist(product);
        logger.info(product);

        em.getTransaction().commit();
        em.close();

    }
}
