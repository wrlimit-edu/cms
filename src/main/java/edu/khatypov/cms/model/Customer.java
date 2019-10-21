package edu.khatypov.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Customer {
    @Id
    private String id;
    private Person person;
    private int number;
    private String phone;
    private String address;
    private CustomerDiscount customerDiscount;
    private boolean enabled;

    public Customer() {
    }

    public Customer(Person person, int number, String phone, String address, CustomerDiscount customerDiscount, boolean enabled) {
        this.person = person;
        this.number = number;
        this.phone = phone;
        this.address = address;
        this.customerDiscount = customerDiscount;
        this.enabled = enabled;
    }

    public Customer(String id, Person person, int number, String phone, String address, CustomerDiscount customerDiscount, boolean enabled) {
        this.id = id;
        this.person = person;
        this.number = number;
        this.phone = phone;
        this.address = address;
        this.customerDiscount = customerDiscount;
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerDiscount getCustomerDiscount() {
        return customerDiscount;
    }

    public void setCustomerDiscount(CustomerDiscount customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", person=" + person +
                ", number=" + number +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", customerDiscount=" + customerDiscount +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

