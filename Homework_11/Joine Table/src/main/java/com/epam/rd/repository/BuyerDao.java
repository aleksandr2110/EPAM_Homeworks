package com.epam.rd.repository;

import com.epam.rd.domain.Buyer;

public interface BuyerDao {

    void create(Buyer buyer);
    Buyer find(Long id);
}
