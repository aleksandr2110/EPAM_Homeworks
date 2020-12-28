package com.epam.rd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="credit_card")
public class CreditCard extends BillingDetails {

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "exp_year")
    private int expYear;

    @Column(name = "exp_month")
    private int expMonth;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }
}
