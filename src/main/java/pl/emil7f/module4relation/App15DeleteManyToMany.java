package pl.emil7f.module4relation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Attribute;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

/**
 * 15. ManyToMany Delete
 * Product & Attribute
 * Hibernate zawsze usuwa powiązania automatycznie po stronie właściciela relacji, jeżeli usuwamy właściciela!
 *
 *  w pętli można zastosować new ArrayList jak dostaniemy błąd ConcurrentModificationException
 *  Opakowywujemy naszego seta/kolecje w inną liste (żeby nie iterować po liście z której usuwamy)
 */
public class App15DeleteManyToMany {


    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Attribute attribute = em.find(Attribute.class, 1L);
        for (Product product : new ArrayList<>(attribute.getProducts())) {

            attribute.removeProduct(product);
        }

        em.remove(attribute);


        em.getTransaction().commit();
        em.close();


    }


}
