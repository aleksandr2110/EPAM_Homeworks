package com.epam.rd.repository;

import com.epam.rd.domain.BankAccount;

/**
 * Created by Aleksandr on 24.12.2020.
 */
public interface BankAccountDao {

    void create(BankAccount bankAccount);
    BankAccount get(Long accountId);
}
