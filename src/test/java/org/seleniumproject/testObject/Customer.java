package org.seleniumproject.testObject;

import com.github.javafaker.Faker;

public class Customer {

    private int customerID;
    private String fullName;
    private String contactName;
    private String address;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    private String city;
    private String zipCode;
    private String countryCode;

    public Customer() {
        Faker faker = new Faker();
        this.fullName = faker.name().fullName();
        this.contactName = faker.superhero().name();
        this.address = faker.address().fullAddress();
        this.city = faker.address().city();
        this.zipCode = faker.address().zipCode();
        this.countryCode = faker.address().countryCode();
    }
}
