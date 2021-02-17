package pl.emil7f.module4relation;

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

/**
 * 2. Tworzymy encje, plik schema.sql, a w MySQL Workbench wklejamy skrypt ze schema.sql
 * Zapis encji, tworzymy produkt. Żeby go zapisać używamy metody persist(product)
 */
public class App01Create {


    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = new Product();
        product.setName("Rower 01");
        product.setDescription("Opis produktu 01");
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        product.setPrice(new BigDecimal("379.99"));
        product.setProductType(ProductType.REAL);

        em.persist(product);
        logger.info(product);

        em.getTransaction().commit();
        em.close();


    }


}
