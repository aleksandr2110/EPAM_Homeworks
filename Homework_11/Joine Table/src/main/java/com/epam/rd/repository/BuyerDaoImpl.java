package com.epam.rd.repository;

import com.epam.rd.domain.Buyer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BuyerDaoImpl implements BuyerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Buyer buyer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(buyer);
    }

    @Override
    public Buyer find(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Buyer buyer = session.find(Buyer.class, id);
        return buyer;
    }
}
