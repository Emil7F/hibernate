package pl.emil7f;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Runner {

     /**
     * 1. Po dodaniu zależności hibernate-core Tworzymy Entity manager, jeśli już go mamy możemy sobie stworzyć sesje hibernate
     * Tworzymy entityManagera i możemy zacząć wykonywać jakieś operacje
     * Jeśli chcemy stworzyć jakieś transakcje to poprzez getTransaction.begin() rozpoczynamy sesje
     * Na koniec trzeba zrobić commit i zamknąć entityManager.close();
     * W ten sposób przygotowana aplikacja powinna się łączyć z bazą danych
     */
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.getTransaction().commit();
        em.close();


    }

}
