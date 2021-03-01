package pl.emil7f.module7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class App33SelectCriteriaApi {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        // "select c from Customer c "

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);

        ParameterExpression<Long> id = criteriaBuilder.parameter(Long.class);

        customerRoot.fetch("orders", JoinType.INNER);

        criteriaQuery.select(customerRoot).distinct(true)
                // where c.id=:id
        .where(criteriaBuilder.equal(customerRoot.get("id"), id));

        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        query.setParameter(id, 1L);

        List<Customer> results = query.getResultList();
        for (Customer result : results) {
            logger.info(result);
            logger.info(result.getOrders());
        }

        em.getTransaction().commit();
        em.clear();

    }
}
