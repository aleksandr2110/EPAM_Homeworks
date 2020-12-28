package com.epam.rd.repository;

import com.epam.rd.domain.BankAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(BankAccount bankAccount) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bankAccount);
    }

    @Override
    public BankAccount get(Long accountId) {
        Session session = sessionFactory.getCurrentSession();
        BankAccount bankAccount = session.get(BankAccount.class, accountId);
        return bankAccount;
    }
}
