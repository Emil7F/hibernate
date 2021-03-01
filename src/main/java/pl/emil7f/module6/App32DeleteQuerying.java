package pl.emil7f.module6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App32DeleteQuerying {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(App32DeleteQuerying.class);

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        int deleted = em.createQuery("delete from Review r" +
                " where r.product.id=:id")
                .setParameter("id", 2L)
                .executeUpdate();

        logger.info(deleted);

        em.getTransaction().commit();
        em.clear();
    }
}
