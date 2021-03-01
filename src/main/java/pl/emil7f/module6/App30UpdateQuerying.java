package pl.emil7f.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
update do zmiany pola tą samą wartością
takie zapytanie można stosować gdy są jasno określine reguły wyszukiwania (np. dla konkretnego id)
W takim przypadku zapytanie będzie działać dużo szybciej niż zapytanie batch'owe
 */

public class App30UpdateQuerying {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    public static Logger logger = LogManager.getLogger(App30UpdateQuerying.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        int updated = em.createQuery("update Review r set r.rating=:rating " +
                " where r.product.id=:id")
                .setParameter("rating", 12)
                .setParameter("id", 1L)
                .executeUpdate();

        logger.info(updated);

        em.getTransaction().commit();
        em.close();
    }
}
