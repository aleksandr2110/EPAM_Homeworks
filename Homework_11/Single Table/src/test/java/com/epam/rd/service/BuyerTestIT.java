package com.epam.rd.service;

import com.epam.rd.HibernateConfig;
import com.epam.rd.domain.Buyer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class BuyerTestIT {

    @Autowired
    private BuyerService buyerService;


    @Test
    public void testFindBuyer() {
        Buyer buyer = new Buyer();
        buyer = buyerService.find(2L);
        assertEquals("Anton", buyer.getFirstName());
    }
}
