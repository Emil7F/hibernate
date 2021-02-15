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
 * 12. ManyToMany Add
 *
 * Najlepiej zamienić List na Set ze względu na to że hibernate nie jest pewny czy dane elementy listy są unikalne
 * wiec na początku wszystkie usuwa i dodaje na nowo, co może powodować problemy wydajnościowe
 */
public class App12AddManyToMany {


    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 5L);
        Attribute attribute = em.find(Attribute.class, 1L);
        product.addAttributes(attribute);

        em.getTransaction().commit();
        em.close();


    }


}
