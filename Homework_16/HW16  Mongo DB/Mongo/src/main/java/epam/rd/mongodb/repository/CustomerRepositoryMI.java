package epam.rd.mongodb.repository;


import epam.rd.mongodb.model.Address;
import epam.rd.mongodb.model.Customer;
import epam.rd.mongodb.model.CustomerDetail;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepositoryMI {

    List<Customer> getAllUsers();
    Customer getCustomerById(String customerId);
    List<Customer> getCustomersByFirstLast(String firstName, String lastName);
    long updateCustomer(Customer customer);
    Customer addNewCustomer(Customer customer);
    List<Customer> findCustomersAddress(Address address);
    List<Customer> findCustomersAccount(LocalDate localDate);

}
