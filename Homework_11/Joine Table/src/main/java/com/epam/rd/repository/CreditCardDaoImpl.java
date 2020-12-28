package com.epam.rd.repository;

import com.epam.rd.domain.CreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CreditCardDaoImpl implements CreditCardDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(CreditCard creditCard) {
        Session session = sessionFactory.getCurrentSession();
        session.save(creditCard);
    }

    @Override
    public CreditCard get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        CreditCard creditCard = session.get(CreditCard.class, id);
        return creditCard;
    }
}
