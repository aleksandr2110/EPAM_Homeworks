package epam.rd.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Address")
public class Address {

    private String street;
    private String city;
    private String countryCode;

    public Address() { }

    public Address(String street, String city, String countryCode) {
        this.street = street;
        this.city = city;
        this.countryCode = countryCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
