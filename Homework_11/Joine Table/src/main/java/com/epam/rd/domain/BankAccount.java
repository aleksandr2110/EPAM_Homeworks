package com.epam.rd.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bank_account")
public class BankAccount extends BillingDetails {

    @Column(name = "account")
    private String account;

    @Column(name = "bank_name")
    private String bankName;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
