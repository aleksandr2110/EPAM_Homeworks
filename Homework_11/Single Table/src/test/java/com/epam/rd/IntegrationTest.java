package com.epam.rd;

import com.epam.rd.domain.BankAccount;
import com.epam.rd.domain.BillingDetails;
import com.epam.rd.domain.Buyer;
import com.epam.rd.domain.CreditCard;
import com.epam.rd.service.BankAccountService;
import com.epam.rd.service.BuyerService;
import com.epam.rd.service.CreditCardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

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
        Buyer buyer = new Buyer();
        buyer.setFirstName("Andrey");
        buyer.setLastName("Proshenko");
        buyerService.create(buyer);
        Buyer buyer2 = new Buyer();
        buyer2.setFirstName("Anton");
        buyer2.setLastName("Ivashun");
        buyerService.create(buyer2);
    }

   @Test
   public void testAddBillings(){
       Buyer buyer = new Buyer();
       buyerService.find(1L);
       CreditCard creditCard = new CreditCard();
       creditCard.setCardNumber("1562741");
       creditCard.setExpYear(2025);
       creditCard.setExpMonth(2);
       creditCard.setBuyer(buyer);
       creditCard.setBillDetailsId(buyer.getId());
       creditCardService.create(creditCard);
       BankAccount bankAccount = new BankAccount();
       bankAccount.setAccount("19022");
       bankAccount.setBankName("PrivatBank");
       bankAccount.setBuyer(buyer);
       bankAccount.setBillDetailsId(buyer.getId());
       bankAccountService.create(bankAccount);
   }

   @Test
   public void printBillings() {
       Buyer buyer = new Buyer();
       buyerService.find(1L);
       Collection<BillingDetails> buyerBillingDetails = buyer.getBillingDetails();
       buyerBillingDetails.forEach(System.out::println);
       buyerBillingDetails.forEach(p -> {
           if (p instanceof CreditCard) logger.info("CreditCard with id: {}", p.getId());
           if (p instanceof BankAccount) logger.info("BankAccount with id: {}", p.getId());
       });
   }
}
