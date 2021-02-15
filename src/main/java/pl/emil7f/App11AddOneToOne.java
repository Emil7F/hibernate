package pl.emil7f;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Attribute;
import pl.emil7f.entity.Category;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 11. OneToOne Add
 * Dodawanie całkowicie nowej kategorii kaskadowo
 */
public class App11AddOneToOne {


    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 3L);
        Category category = new Category();
        category.setName("Nowa Kategoria");
        category.setDescription("Opis Kategorii");
        em.persist(category);           // transient
        product.setCategory(category);

        em.getTransaction().commit();
        em.close();


    }


}
