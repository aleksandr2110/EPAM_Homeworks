package com.epam.rd.service;

import com.epam.rd.domain.CreditCard;
import com.epam.rd.repository.CreditCardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardDao creditCardDao;

    @Override
    @Transactional
    public void create(CreditCard creditCard) {
        creditCardDao.create(creditCard);
    }
}
