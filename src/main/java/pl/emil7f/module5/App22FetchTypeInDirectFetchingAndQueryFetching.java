package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Jak mogą się zmieniać zapytania gdy korzystamy z róznych parametrów zapytania FetchType - find()
 * Category pobiera się leniwie
 *
 Hibernate:
 select
 product0_.id as id1_5_0_,
 product0_.category_id as category8_5_0_,
 product0_.created as created2_5_0_,
 product0_.description as descript3_5_0_,
 product0_.name as name4_5_0_,
 product0_.price as price5_5_0_,
 product0_.type as type6_5_0_,
 product0_.updated as updated7_5_0_
 from
 Product product0_
 where
 product0_.id=?
 2021-02-21 20:27:52.589 INFO  [main] App22FetchTypeInDirectFetchingAndQueryFetching - Product{id=1, name='Rower 01', description='To jest opis produktu', created=2020-07-22T15:29:39, updated=2020-07-22T15:29:39, price=19.99, productType=REAL}

 Hibernate:
 select
 category0_.id as id1_1_0_,
 category0_.description as descript2_1_0_,
 category0_.name as name3_1_0_
 from
 Category category0_
 where
 category0_.id=?
 2021-02-21 20:27:52.594 INFO  [main] App22FetchTypeInDirectFetchingAndQueryFetching - Category{id=1, name='Kategoria 1', description='Opis 1'}

 Process finished with exit code 0

 * */

public class App22FetchTypeInDirectFetchingAndQueryFetching {

    private static Logger logger = LogManager.getLogger(App22FetchTypeInDirectFetchingAndQueryFetching.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 1L);

        logger.info(product);
        logger.info(product.getCategory());


        em.getTransaction().commit();
        em.close();
    }

}
