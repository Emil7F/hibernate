package pl.emil7f.module4relation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 7. OneToMany usuwanie kaskadowe
 */
public class App07OneToManyDelete {


    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static final Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 1L);
        em.remove(product);
        // zadziała to od razu na bazie danych ponieważ został ustawiony w tabeli Review do klucza obcego parametr ON DELETE CASCADE
        // Nie wszyskie bazy danych wspierają takie usuwanie klucza obcego i można natrafić na błąd
        // mówiący o tym że nie można usunąc produktu z tab Product ponieważ są przypisane do niego wiersze z tabeli Review
        // można też to ustawić w Encji w sekcji @OneToMany(cascade = CascadeType.REMOVE) lub inne takie jak nam są potrzebne
        // Najpierw zostaną usunięte opinie, a dopiero później sam produkt



        em.getTransaction().commit();
        em.close();


    }


}
