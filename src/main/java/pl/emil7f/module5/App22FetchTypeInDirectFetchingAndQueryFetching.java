package pl.emil7f.module5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Jak mogą się zmieniać zapytania gdy korzystamy z róznych parametrów zapytania FetchType - find()
 * Category pobiera się gorliwie
 * Reviews pobiera się gorliwie
 * Hibernate generuje left outer join przy zapytaniu do Category oraz dla Reviews
 * czyli w pojedyńczym select'cie probuje on optymalizować pobieranie powiązanych relacji poprzez join'a
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
 product0_.updated as updated7_5_0_,
 category1_.id as id1_1_1_,
 category1_.description as descript2_1_1_,
 category1_.name as name3_1_1_,
 reviews2_.product_id as product_4_7_2_,
 reviews2_.id as id1_7_2_,
 reviews2_.id as id1_7_3_,
 reviews2_.content as content2_7_3_,
 reviews2_.product_id as product_4_7_3_,
 reviews2_.rating as rating3_7_3_
 from
 Product product0_
 left outer join
 Category category1_
 on product0_.category_id=category1_.id
 left outer join
 Review reviews2_
 on product0_.id=reviews2_.product_id
 where
 product0_.id=?
 2021-02-21 20:37:22.250 INFO  [main] App22FetchTypeInDirectFetchingAndQueryFetching - Product{id=1, name='Rower 01', description='To jest opis produktu', created=2020-07-22T15:29:39, updated=2020-07-22T15:29:39, price=19.99, productType=REAL}
 2021-02-21 20:37:22.251 INFO  [main] App22FetchTypeInDirectFetchingAndQueryFetching - Category{id=1, name='Kategoria 1', description='Opis 1'}
 2021-02-21 20:37:22.251 INFO  [main] App22FetchTypeInDirectFetchingAndQueryFetching - [Review{id=1, content='Treść opinii 1', rating=5}, Review{id=4, content='Treść opinii 4', rating=5}, Review{id=2, content='Treść opinii 2', rating=5}, Review{id=3, content='Treść opinii 3', rating=5}, Review{id=5, content='Treść opinii 5', rating=5}]

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
        logger.info(product.getReviews());


        em.getTransaction().commit();
        em.close();
    }

}
