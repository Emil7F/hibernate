package pl.emil7f;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.Runner;
import pl.emil7f.entity.Product;
import pl.emil7f.entity.ProductType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 5. Relacja OneToMany
 * Jak pobrać liste produktów w JPA?  poprzez em.createQuery("query in jpql");
 *
 * Tak stworzona aplikacja działa lecz wykonuje select kilka razy co określane jest problemem n+1 zapytań
 * Problem powstaje ze wzgledu na fakt że Hibernate wykorzystuje LazyLoading który sprawia że hibernate pobiera encje
 * dopiero wtedy gdy o nie poprosimy
 * Można to przełączyć w encji na której operujemy. W opisie relacji ustawiamy FetchType.EAGER, domyślnie jest LAZY
 * @OneToMany(fetch = FetchType.EAGER)
 * Nie jest to do konca optymalne bo dalej występuje problem n+1
 * */
public class App05OneToMany {


    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {


        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Product> products = em.createQuery("select p from Product p").getResultList();
        for(Product product: products){
            logger.info(product.toString());
        }

        em.getTransaction().commit();
        em.close();


    }


}
