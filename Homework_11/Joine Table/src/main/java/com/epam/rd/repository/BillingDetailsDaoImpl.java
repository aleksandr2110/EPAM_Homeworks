package com.epam.rd.repository;

import com.epam.rd.domain.BillingDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BillingDetailsDaoImpl implements BillingDetailsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(BillingDetails billingDetails) {
        Session session = sessionFactory.getCurrentSession();
        session.save(billingDetails);
    }
}
