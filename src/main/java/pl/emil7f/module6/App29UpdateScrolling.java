package pl.emil7f.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;
import pl.emil7f.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Stream;

/*
Pobieramy całą tabele review, pobrane encje przechowuje w kontekście. Jeśli rekordów jest dużo to może wyskoczyć błąd OutOfMemoryError
W takiej formie nie możemy skorzystać z flush i clear bo dane mamy w pamięci sesji.

Aby zooptymalizować wykonanie zapytania można użyć scrollowania (strumieniowania)
Jeśli nie ustawimy hinta to niekoniecznie będziemy korzystać ze strumieniowania z db, bo domyślnie metoda uzywa stream na liście

 default Stream<X> getResultStream() {
        return getResultList().stream();
    }
 */

public class App29UpdateScrolling {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App29UpdateScrolling.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery(
                "select r from BatchReview r ",
                BatchReview.class
        )
                .setHint(QueryHints.HINT_FETCH_SIZE, Integer.MIN_VALUE)
                .getResultStream()
                .forEach(batchReview -> {
                    batchReview.setContent("Content");
                    batchReview.setRating(5);
                    logger.info(batchReview);
                });


        em.getTransaction().commit();
        em.close();
    }
}
