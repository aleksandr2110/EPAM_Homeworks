package epam.rd.mongodb.model;


import java.time.LocalDate;


public class Account {

    private Long cardNumber;
    private String nameOnAccount;
    private LocalDate expirationDate;

    public Account() {}

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnAccount() {
        return nameOnAccount;
    }

    public void setNameOnAccount(String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "cardNumber=" + cardNumber +
                ", nameOnAccount='" + nameOnAccount + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
