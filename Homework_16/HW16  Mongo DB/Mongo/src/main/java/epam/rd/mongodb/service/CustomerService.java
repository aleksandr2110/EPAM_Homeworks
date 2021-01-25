package epam.rd.mongodb.service;


import epam.rd.mongodb.model.Address;
import epam.rd.mongodb.model.Customer;
import epam.rd.mongodb.model.CustomerDetail;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer create(Customer customer);
    Customer findById(String id);
    List<Customer> getCustomersByFirstLast(String firstName, String lastName);
    long updateCustomer(Customer customer) ;
    List<Customer> findCustomersAddress(Address address);
    List<Customer> findCustomersAccount(LocalDate localDate);

}
