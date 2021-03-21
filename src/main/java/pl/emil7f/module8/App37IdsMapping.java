package pl.emil7f.module8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Customer;
import pl.emil7f.entity.CustomerDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class App37IdsMapping {

    private static Logger logger = LogManager.getLogger(App37IdsMapping.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();;

        Customer customer = em.find(Customer.class, 1L);
//        CustomerDetails customerDetails = new CustomerDetails();
//        customerDetails.setBirthPlace("Wrocław");
//        customerDetails.setBirthDay(LocalDate.of(2000,10,22));
//        customerDetails.setFatherName("Roman");
//        customerDetails.setMotherName("Agnieszka");
//        customerDetails.setPesel("1234567890");
//        customerDetails.setCustomer(customer);
//        customer.setCustomerDetails(customerDetails);
//        em.persist(customer);

        logger.info(customer);
        logger.info(customer.getCustomerDetails());




        em.getTransaction().commit();
        em.close();
    }

}
