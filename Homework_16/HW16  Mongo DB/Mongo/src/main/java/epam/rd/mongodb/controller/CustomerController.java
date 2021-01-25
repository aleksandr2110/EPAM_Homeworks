package epam.rd.mongodb.controller;

import epam.rd.mongodb.model.Address;
import epam.rd.mongodb.model.Customer;
import epam.rd.mongodb.model.CustomerDetail;
import epam.rd.mongodb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

@RestController
public class CustomerController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private CustomerService customerService;

    public CustomerController() {}

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // http://localhost:8102/create-customer
    @PostMapping(value = "/create-customer",  consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void createCustomer(@RequestBody Customer customer)
    {
        LOG.info("Saving customer");
        customerService.create(customer);
    }

    // http://localhost:8102/update-customer/600d8b617e990278a9744fe4
    @PutMapping(value = "/update-customer/{id}",  consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Customer customer)
    {
        LOG.info("Update customer");
        HashMap<String, Object> resp = new HashMap<>();
        customerService.updateCustomer(customer);
        resp.put("customer", customer);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    // http://localhost:8102/all
    @GetMapping(value="/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> findAll() {
        LOG.info("Getting all customers");
        return customerService.findAll();
    }

    // http://localhost:8102/customer-id?id=600d8b617e990278a9744fe4
    @GetMapping(value="/customer-id", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer findCustomerById(@RequestParam(value="id") String customerId)
    {
        LOG.info("Getting customer by id");
        return customerService.findById(customerId);
    }

    // http://localhost:8102/customer-first-last?firstName=Ivan&lastName=Miheev
    @GetMapping(value="/customer-first-last", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomersByFirstLast(String firstName, String lastName)
    {
        LOG.info("Getting customer(s) by first and last name");
        return customerService.getCustomersByFirstLast(firstName, lastName);
    }

    //  http://localhost:8102/customer-by-address?street=Tarasa56&city=Dnipro&countryCode=14811
    @GetMapping(value="/customer-by-address",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> findCustomersAddress(String street, String city, String countryCode)
    {
        LOG.info("Getting customer(s) by address");
        Address address = new Address(street, city, countryCode);
        return customerService.findCustomersAddress(address);
    }

    //  http://localhost:8102/customer-by-expired?expirationDate=25-08-2020
    @GetMapping(value="/customer-by-expired",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> findCustomersAccount(@RequestParam (name = "expirationDate") String expDate) {
        LOG.info("Getting customer(s) by expired date of card");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate localDate = LocalDate.parse(expDate, formatter);
        return customerService.findCustomersAccount(localDate);
    }

}
