package pl.emil7f.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import pl.emil7f.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/*
Pobieramy całą tabele review, pobrane encje przechowuje w kontekście. Jeśli rekordów jest dużo to może wyskoczyć błąd OutOfMemoryError
W takiej formie nie możemy skorzystać z flush i clear bo dane mamy w pamięci sesji.

Aby zooptymalizować wykonanie zapytania można skorzystać z metody setFirstResult and setMaxResult,
tak aby postronicować wyniki i pobrać procjami
 */

public class App28UpdateAndPaging {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App28UpdateAndPaging.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Long count = em.createQuery(
                "select count(r) from BatchReview r ",
                Long.class)
                .getSingleResult();
        int batchSize = 10;
        em.unwrap(Session.class).setJdbcBatchSize(batchSize);

        for (int i = 0; i < count; i = i + batchSize) {
            List<BatchReview> review = em.createQuery(
                    "select r from BatchReview r ",
                    BatchReview.class
            )
                    .setFirstResult(i)
                    .setMaxResults(batchSize)
                    .getResultList();
            logger.info(review);
            for (BatchReview batchReview : review) {
                batchReview.setContent("Nowa treść!!!");
                batchReview.setRating(14);
            }
            em.flush();
            em.clear();

        }


        em.getTransaction().commit();
        em.close();
    }
}
