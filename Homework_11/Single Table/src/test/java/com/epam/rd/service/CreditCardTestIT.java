package com.epam.rd.service;

import com.epam.rd.HibernateConfig;
import com.epam.rd.domain.CreditCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class CreditCardTestIT {

    @Autowired
    private CreditCardService creditCardService;

    @Test
    public void testAddCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("15621901");
        creditCard.setExpYear(2024);
        creditCard.setExpMonth(10);
        creditCardService.create(creditCard);
        CreditCard creditCard2 = new CreditCard();
        creditCard2.setCardNumber("1528911");
        creditCard2.setExpYear(2022);
        creditCard2.setExpMonth(8);
        creditCardService.create(creditCard2);
        CreditCard creditCard3 = new CreditCard();
        creditCard3.setCardNumber("1562567");
        creditCard3.setExpYear(2021);
        creditCard3.setExpMonth(11);
        creditCardService.create(creditCard3);

    }
}
