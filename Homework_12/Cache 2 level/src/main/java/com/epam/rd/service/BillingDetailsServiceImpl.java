package com.epam.rd.service;

import com.epam.rd.domain.BillingDetails;
import com.epam.rd.repository.BillingDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingDetailsServiceImpl implements BillingDetailsService {

    @Autowired
    private BillingDetailsDao billingDetailsDao;

    @Override
    public void create(BillingDetails billingDetails) {
        billingDetailsDao.create(billingDetails);
    }

}
