package com.epam.rd.service;

import com.epam.rd.domain.BankAccount;

/**
 * Created by Aleksandr on 24.12.2020.
 */
public interface BankAccountService {

    void create(BankAccount bankAccount);
    BankAccount get(Long accountId);
}
