package com.epam.rd.service;

import com.epam.rd.HibernateConfig;
import com.epam.rd.domain.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class BankAccountTestIT {

    @Autowired
    private BankAccountService bankAccountService;

    @Test
    public void testAddBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount("12451");
        bankAccount.setBankName("Idea Bank");
        bankAccountService.create(bankAccount);
    }
}
