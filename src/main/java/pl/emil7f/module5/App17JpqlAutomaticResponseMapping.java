package pl.emil7f.module5;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.ProductInCategoryCounterDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class App17JpqlAutomaticResponseMapping {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App17JpqlAutomaticResponseMapping.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(
                "select new pl.emil7f.entity.ProductInCategoryCounterDto(p.category.id, count(p)) " +
                        " from Product p group by p.category"
        );

        List<ProductInCategoryCounterDto> resultList = query.getResultList();
        for (ProductInCategoryCounterDto dto : resultList) {
            logger.info(dto.getCategoryId() + " , " + dto.getProductInCategoryCounter());
        }


        em.getTransaction().commit();
        em.close();

    }
}
