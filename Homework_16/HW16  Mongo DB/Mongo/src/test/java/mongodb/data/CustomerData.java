package com.mongodb.data;

import epam.rd.mongodb.model.Account;
import epam.rd.mongodb.model.Address;
import epam.rd.mongodb.model.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class CustomerData {

    public Customer customer() {
        Customer customer = new Customer();
        customer.setFirstName("Aleksandr");
        customer.setLastName("Olejnik");

        Address address = new Address();
        address.setStreet("Bogdana24");
        address.setCity("Dnipro");
        address.setCountryCode("14811");
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(address);
        customer.setAddress(addresses);

        Account account = new Account();
        account.setCardNumber(123121213L);
        account.setExpirationDate(LocalDate.of(2021, 12, 12));
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(account);
        customer.setAccounts(accounts);
        return customer;
    }

}
