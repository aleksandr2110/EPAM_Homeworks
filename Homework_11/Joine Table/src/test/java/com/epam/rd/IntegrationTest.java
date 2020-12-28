package com.epam.rd;

import com.epam.rd.domain.BankAccount;
import com.epam.rd.domain.BillingDetails;
import com.epam.rd.domain.Buyer;
import com.epam.rd.domain.CreditCard;
import com.epam.rd.service.BankAccountService;
import com.epam.rd.service.BillingDetailsService;
import com.epam.rd.service.BuyerService;
import com.epam.rd.service.CreditCardService;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
public class IntegrationTest {

   private static Logger logger =  LoggerFactory.getLogger(IntegrationTest.class);
   @Autowired
   private BuyerService buyerService;

   @Autowired
   private CreditCardService creditCardService;

   @Autowired
   private BankAccountService bankAccountService;

    @Test
    public void testAddBuyers() {
        Buyer buyer1 = new Buyer();
        buyer1.setFirstName("Andrey");
        buyer1.setLastName("Proshenko");
        buyerService.create(buyer1);
        Buyer buyer2 = new Buyer();
        buyer2.setFirstName("Anton");
        buyer2.setLastName("Ivashun");
        buyerService.create(buyer2);
        Buyer buyer3 = new Buyer();
        buyer3.setFirstName("Aleksandr");
        buyer3.setLastName("Doroxin");
        buyerService.create(buyer3);
        Buyer buyer4 = new Buyer();
        buyer4.setFirstName("Viktoria");
        buyer4.setLastName("Braun");
        buyerService.create(buyer4);
        Buyer buyer  = buyerService.find(1L);
        assertEquals("Andrey", buyer.getFirstName());
    }

    @Test
    public void testAddToBillDetails() {
        Buyer buyer = buyerService.find(1L);
        CreditCard creditCard = new CreditCard();
        creditCard.setBillDetailsId(buyer.getId());
        creditCard.setCardNumber("1562722");
        creditCard.setExpYear(2025);
        creditCard.setExpMonth(2);
        logger.info("credit card: {}", creditCard.getBillDetailsId());
        creditCardService.create(creditCard);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBillDetailsId(buyer.getId());
        bankAccount.setAccount("19091");
        bankAccount.setBankName("PrivatBank");
        logger.info("bank account : {}", bankAccount.getBillDetailsId());
        bankAccountService.create(bankAccount);
    }

    @Test
    public void testAddedBillingsDetails(){
        CreditCard creditCardDB = creditCardService.get(1L);
        BankAccount bankAccountDB = bankAccountService.get(2L);
        assertEquals(2025, creditCardDB.getExpYear());
        assertEquals("19091", bankAccountDB.getAccount());
    }

    @Test
    @Transactional
    public void printBillingsCollection() {
        Buyer buyer = buyerService.find(1L);
        logger.info("Billing details information :");
        Collection<BillingDetails> buyerBillingDetails = buyer.getBillingDetails();
        buyerBillingDetails.forEach(p -> {
           if (p instanceof CreditCard) logger.info("CreditCard with bill_id: {}", p.getId());
           if (p instanceof BankAccount) logger.info("BankAccount with bill_id: {}", p.getId());
        });
    }
}
