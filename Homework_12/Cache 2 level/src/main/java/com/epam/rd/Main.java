package com.epam.rd;

import com.epam.rd.domain.Buyer;
import com.epam.rd.domain.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String PERSISTENCE_UNIT_NAME = "single_table";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        Long id = addBuyer(factory);

        // testAddCreditCard(factory);
        EntityManager entityManager = factory.createEntityManager();
        Buyer buyer_1 = entityManager.find(Buyer.class, id);
        logger.info("{}", buyer_1); // Buyer@e322ec9

        printCacheInfo();

        entityManager = factory.createEntityManager();
        Buyer buyer_2 = entityManager.find(Buyer.class, id);
        logger.info("{}", buyer_2); // Buyer@20411320
        System.out.println(buyer_1 == buyer_2);
    }

    private static void printCacheInfo() {
        List<CacheManager> cacheManagers = CacheManager.ALL_CACHE_MANAGERS;
        if (!cacheManagers.isEmpty()) {
            CacheManager cacheManager = cacheManagers.get(0);
            Cache buyersCache = cacheManager.getCache(Buyer.class.getName());
            logger.info("Buyers second level cache has size = {}", buyersCache.getSize());
        } else {
            logger.info("Hibernate second level cache is disabled.");
        }
    }

    private static Long addBuyer(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            Buyer buyer = new Buyer();
            buyer.setFirstName("Joshua");
            buyer.setLastName("Bloch");

            entityManager.persist(buyer);

            tx.commit();
            return buyer.getId();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }
    }

    public void testAddCreditCard(EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            CreditCard creditCard = new CreditCard();
            creditCard.setCardNumber("15621901");
            creditCard.setExpYear(2024);
            creditCard.setExpMonth(10);

            entityManager.persist(creditCard);

            CreditCard creditCard2 = new CreditCard();
            creditCard2.setCardNumber("1528911");
            creditCard2.setExpYear(2022);
            creditCard2.setExpMonth(8);

            entityManager.persist(creditCard2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        }

    }
}