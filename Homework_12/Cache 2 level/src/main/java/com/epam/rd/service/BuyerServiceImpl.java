package com.epam.rd.service;

import com.epam.rd.domain.Buyer;
import com.epam.rd.repository.BuyerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerDao buyerDao;

    @Override
    @Transactional
    public void create(Buyer buyer) {
        buyerDao.create(buyer);
    }

    @Override
    @Transactional
    public Buyer find(Long id) {
        Buyer buyer = buyerDao.find(id);
        return buyer;
    }
}
