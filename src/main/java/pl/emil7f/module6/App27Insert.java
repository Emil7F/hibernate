package pl.emil7f.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import pl.emil7f.entity.batch.BatchReview;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
W przypadku MySql obsługa batchowania nie jest wygodna
Hibernate wyłącza mechanizm batchowania dla encji w których typ batchowania to IDENTITY
Najwygodniej to robić na db która wspiera sekwencje -> GenerationType.SEQUENCE, np w PostgreSQL
wtedy ustawiamy odpowiednio properties batch_size.
Nie trzeba wtedy bawić się w budowanie dodatkowych encji oraz w zapytania po ostatni ID żeby dodać nowy wiersz w tabeli

Hibernate cache'uje sobie encje w sesji, każda encja tworzona w pętli jest w cache'u sesji, do zamknięcia sesji
Jesli będziemy dodawać setki rekordów to możemy zapchać pamięć
Można temu zapobiec poprze czyszczenie sesji z obietków batchReview co jakiś czas
 */

public class App27Insert {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App27Insert.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.unwrap(Session.class).setJdbcBatchSize(10);  //  unwrap rozpakowywuje obiekt sesji hibernate i można ustawić batchSize sesji
        em.getTransaction().begin();

        Long lastId = em.createQuery("select max(r.id) from BatchReview r ", Long.class).getSingleResult();

        for (long i = 1; i <= 25; i++) {
            if(i % 5 == 0){
                em.flush();    // zapis danych wszystkich niezapisanych encji
                em.clear();   // co 5 elementów nastąpi przeładowanie i czyszczenie sesji
            }
            em.persist(new BatchReview(lastId + i, "Treść " + i, (int) (i + 5 % 2), 1L));

        }

        em.getTransaction().commit();
        em.close();
    }
}
