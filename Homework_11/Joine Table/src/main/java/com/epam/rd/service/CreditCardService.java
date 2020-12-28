package com.epam.rd.service;


import com.epam.rd.domain.CreditCard;

public interface CreditCardService {

    void create(CreditCard creditCard);
    CreditCard get(Long id);
}
