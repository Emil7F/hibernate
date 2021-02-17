package pl.emil7f.module4relation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Runner {

     /**
     * 1. Po dodaniu zależności hibernate-core Tworzymy Entity manager, jeśli już go mamy możemy sobie stworzyć sesje hibernate
     * Tworzymy entityManagera i możemy zacząć wykonywać jakieś operacje  (odpowiednik SessionFactory)
     * Jeśli chcemy stworzyć jakieś transakcje to poprzez getTransaction.begin() rozpoczynamy sesje
     * Na koniec trzeba zrobić commit i zamknąć entityManager.close();
     * W ten sposób przygotowana aplikacja powinna się łączyć z bazą danych
     */
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        logger.info("Logger test");

        em.getTransaction().commit();
        em.close();

    }

}
