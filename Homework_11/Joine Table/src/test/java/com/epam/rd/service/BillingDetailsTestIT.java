package com.epam.rd.service;

import com.epam.rd.HibernateConfig;
import com.epam.rd.domain.BillingDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class BillingDetailsTestIT {

    private BillingDetails billingDetails;
/*
    @Autowired
    private BillingDetailsService billingDetailsService;

    @Test
    public void testAddBillingDetails() {
        BillingDetails billingDetails = new BillingDetails();
        //billingDetails.setId(1L);
        billingDetailsService.create(billingDetails);
    } */
}
