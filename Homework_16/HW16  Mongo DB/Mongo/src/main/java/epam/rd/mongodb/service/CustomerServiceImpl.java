package epam.rd.mongodb.service;

import epam.rd.mongodb.model.Address;
import epam.rd.mongodb.model.Customer;
import epam.rd.mongodb.model.CustomerDetail;
import epam.rd.mongodb.repository.CustomerRepositoryMI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepositoryMI customerRepository;

    public CustomerServiceImpl() {}

    @Autowired
    public CustomerServiceImpl(CustomerRepositoryMI customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.addNewCustomer(customer);
    }

    @Override
    public long updateCustomer(Customer customer)  {
        return customerRepository.updateCustomer(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.getAllUsers();
    }

    @Override
    public Customer findById(String id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public List<Customer> getCustomersByFirstLast(String firstName, String lastName) {
        return customerRepository.getCustomersByFirstLast(firstName, lastName);
    }

    @Override
    public List<Customer> findCustomersAccount(LocalDate localDate) {
        return customerRepository.findCustomersAccount(localDate);
    }

    @Override
    public List<Customer> findCustomersAddress(Address address) {
        return customerRepository.findCustomersAddress(address);
    }



}
