package pl.emil7f.module7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.emil7f.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class App34CriteriaFiltering {
    private static Logger logger = LogManager.getLogger(App34CriteriaFiltering.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);


        ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);
        ParameterExpression<String> lastname = criteriaBuilder.parameter(String.class);
        ParameterExpression<Collection> ids = criteriaBuilder.parameter(Collection.class);

        Join<Object, Object> orders = (Join<Object, Object>) customerRoot
                .fetch("orders", JoinType.INNER);

        orders.fetch("orderRows")
                .fetch("product");

        criteriaQuery.select(customerRoot).distinct(true)
                .where(
                        criteriaBuilder.and(
                                customerRoot.get("id").in(ids),
                                criteriaBuilder.between(orders.get("total"),
                                        total, criteriaBuilder.literal(new BigDecimal("70.00"))),
                                criteriaBuilder.isNotNull(customerRoot.get("firstname"))
                        )
                );

        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        query.setParameter(total, new BigDecimal(30.00));
        query.setParameter(ids, Arrays.asList(1L, 2L, 4L));

        List<Customer> resultList = query.getResultList();
        for (Customer result : resultList) {
            logger.info(result);
            logger.info(result.getOrders());
        }


        em.getTransaction().commit();
        em.close();
    }
}
