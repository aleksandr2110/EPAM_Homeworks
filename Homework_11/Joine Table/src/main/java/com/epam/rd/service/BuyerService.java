package com.epam.rd.service;


import com.epam.rd.domain.Buyer;

public interface BuyerService {

    void create(Buyer buyer);
    Buyer find(Long id);
}
