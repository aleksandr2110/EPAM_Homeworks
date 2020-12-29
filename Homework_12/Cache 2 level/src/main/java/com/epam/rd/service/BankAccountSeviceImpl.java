package com.epam.rd.service;

import com.epam.rd.domain.BankAccount;
import com.epam.rd.repository.BankAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountSeviceImpl implements BankAccountService {

    @Autowired
    private BankAccountDao bankAccountDao;

    @Override
    public void create(BankAccount bankAccount) {
        bankAccountDao.create(bankAccount);
    }

    @Override
    public BankAccount get(Long accountId) {
        return null;
    }
}
