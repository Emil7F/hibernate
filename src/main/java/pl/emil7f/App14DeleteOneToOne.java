package pl.emil7f;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Product;
import pl.emil7f.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 14. OneToOne Delete
 *  Product & Category
 *  Usunięcie danego produktu nie powinno powodować usunięcia całej kategorii do której należał ten produkt
 *  i odwortnie
 * */
public class App14DeleteOneToOne {


    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 3L);
        if(product.getCategory().getProduct().size()==1){
            em.remove(product.getCategory());
        }
        product.setCategory(null);

        em.getTransaction().commit();
        em.close();


    }


}
