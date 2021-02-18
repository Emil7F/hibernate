package pl.emil7f.module5;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.ProductInCategoryCounterDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App17JpqlAResponseMapping {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App17JpqlAResponseMapping.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(
                "select p.category.id, count(p) from Product p group by p.category"
        );

        List<Object[]> resultList = query.getResultList();

        List<ProductInCategoryCounterDto> result = resultList.stream()
                .map(objects -> new ProductInCategoryCounterDto((Long) objects[0], (Long) objects[1]))
                .collect(Collectors.toList());

        for (ProductInCategoryCounterDto dto : result) {
            logger.info(dto.getCategoryId());
            logger.info(dto.getProductInCategoryCounter());
        }


        em.getTransaction().commit();
        em.close();


    }
}
