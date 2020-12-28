package com.epam.rd.service;

import com.epam.rd.domain.BankAccount;
import com.epam.rd.repository.BankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountSeviceImpl implements BankAccountService {

    @Autowired
    private BankAccountDao bankAccountDao;

    @Override
    @Transactional
    public void create(BankAccount bankAccount) {
        bankAccountDao.create(bankAccount);
    }

    @Override
    @Transactional
    public BankAccount get(Long accountId) {
        return bankAccountDao.get(accountId);
    }
}
