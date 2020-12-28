package com.epam.rd.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="buyer_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<BillingDetails> billingDetails = new ArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public List<BillingDetails> getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(List<BillingDetails> billingDetails) {
        this.billingDetails = billingDetails;
    }
}
