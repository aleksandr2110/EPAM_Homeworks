package com.mongodb.service;

import com.mongodb.data.CustomerData;
import epam.rd.mongodb.model.Customer;
import epam.rd.mongodb.repository.CustomerRepositoryMI;
import epam.rd.mongodb.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustomerServiceImlIT {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepositoryMI customerRepository;

    @Autowired
    private CustomerData customerData;

    @Test
    void shouldFindAllEmployees() {

        //GIVEN
        Customer customer = customerData.customer();
        customerRepository.addNewCustomer(customer);

        //WHEN
        List<Customer> result = customerService.findAll();

        //THEN
        assertNotNull(result);
        assertEquals(1, result.size());
    }


}
