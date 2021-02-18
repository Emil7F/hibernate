package pl.emil7f.module5;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

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
        for (Object[] array : resultList) {
            logger.info(array[0] + " , " + array[1]);
        }


        em.getTransaction().commit();
        em.close();


    }
}
