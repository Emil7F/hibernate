package pl.emil7f.module8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Address;
import pl.emil7f.entity.AddressType;
import pl.emil7f.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Arrays;

public class App36ElementCollection {

    private static Logger logger = LogManager.getLogger(App36ElementCollection.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = new Customer();
        customer.setFirstname("Customer 1");
        customer.setLastname("Customer last name 1");
        customer.setCreated(LocalDateTime.now());
        customer.setUpdated(LocalDateTime.now());
        customer.setAddress(Arrays.asList(
                new Address(AddressType.BILLING, "Polna 10/1", "00-000", "Katowice"),
                new Address(AddressType.SHIPPING, "Szkolna  10/1", "00-000", "Katowice"),
                new Address(AddressType.INVOICE, "Wolna  10/1", "00-000", "Katowice")
                ));
        em.persist(customer);

        em.flush();
        em.clear();


        Customer customer1 = em.find(Customer.class, customer.getId());
        logger.info(customer1.getAddress());


        em.getTransaction().commit();
        em.close();


    }
}
