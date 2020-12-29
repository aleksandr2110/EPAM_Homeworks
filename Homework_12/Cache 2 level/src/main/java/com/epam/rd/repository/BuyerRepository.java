package com.epam.rd.repository;

import com.epam.rd.domain.BillingDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BuyerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(BillingDetails billingsDetails) {
        Session session = sessionFactory.getCurrentSession();
        session.save(billingsDetails);
    }

    /*
    public BillingDetails get(String name_enterprise) {
        Session session = sessionFactory.getCurrentSession();

        Object singleResult = session
                .getNamedQuery("saleByEnterprise")
                .setParameter("nameEnterprise", name_enterprise)
                .getSingleResult();
        return(BillingDetails) singleResult;  */
}
