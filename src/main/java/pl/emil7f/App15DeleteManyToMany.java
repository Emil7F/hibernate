package pl.emil7f;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 15. ManyToMany Delete
 *  Product & Attribute
 *  W poniższy sposób czyścimi powiązania z atrybutami po stronie produktu
 * */
public class App15DeleteManyToMany {


    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 5L);
        // em.remove(product); // zostanie usunięty produkt wraz z przypisanymi do niego opiniami (opinie usuwają się kaskadowo)
        // także zostaną usunięte powiązania do atrybutów ale wszyskie atrybuty zostaną zachowane

        product.getAttributes().clear();


        em.getTransaction().commit();
        em.close();


    }


}
