package epam.rd.mongodb.repository;

import com.mongodb.client.result.UpdateResult;
import epam.rd.mongodb.model.Address;
import epam.rd.mongodb.model.Customer;
import epam.rd.mongodb.model.CustomerDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class CustomerRepositoryMIImpl implements CustomerRepositoryMI {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Customer> getAllUsers() {
        return  mongoTemplate.findAll(Customer.class);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(customerId));
        return mongoTemplate.findOne(query, Customer.class);
    }

    @Override
    public List<Customer> getCustomersByFirstLast(String firstName, String lastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName).and("lastName").is(lastName));
        List<Customer> list = new ArrayList<>();
        list = mongoTemplate.find(query, Customer.class);
        return list;
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        mongoTemplate.save(customer);
        return customer;
    }

    @Override
    public long updateCustomer(Customer customer) {
        Query query = new Query(Criteria.where("id").is(customer.getId()));
        Update update = new Update();
        update.set("firstName", customer.getFirstName());
        update.set("lastName", customer.getLastName());
        UpdateResult result = this.mongoTemplate.updateFirst(query, update, Customer.class);
        if (result != null) {
            return result.getModifiedCount();
        }
        return 0;
    }

    @Override
    public List<Customer> findCustomersAddress(Address address) {
        Query query = new Query();
        Criteria street = Criteria.where("address.street").is(address.getStreet());
        Criteria city = Criteria.where("address.city").is(address.getCity());
        Criteria countryCode = Criteria.where("address.countryCode").is(address.getCountryCode());
        Criteria andCr = new Criteria().andOperator(street, city, countryCode);
        query.addCriteria(andCr);
        List<Customer> list = new ArrayList();
        list = mongoTemplate.find(query, Customer.class);
        return list;
    }

    @Override
    public List<Customer> findCustomersAccount(LocalDate localDate) {
        Query query = new Query();
        Criteria expirationDate = Criteria.where("accounts.expirationDate").lt(localDate);
        query.addCriteria(expirationDate);
        List<Customer> list = new ArrayList();
        list = mongoTemplate.find(query, Customer.class);
        return list;
    }

}
