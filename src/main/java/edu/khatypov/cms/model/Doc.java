package edu.khatypov.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Document
public class Doc {
    @Id
    private String id;
    private int number;
    private LocalDate date;
    private Boolean status;
    private Boolean type;
    private Customer customer;
    private List<Product> products;
    private float sum;

    public Doc() {
    }

    public Doc(int number, LocalDate date, Boolean status, Boolean type, Customer customer, List<Product> products, float sum) {
        this.number = number;
        this.date = date;
        this.status = status;
        this.type = type;
        this.customer = customer;
        this.products = products;
        this.sum = sum;
    }

    public Doc(String id, int number, LocalDate date, Boolean status, Boolean type, Customer customer, List<Product> products, float sum) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.status = status;
        this.type = type;
        this.customer = customer;
        this.products = products;
        this.sum = sum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", date=" + date +
                ", status=" + status +
                ", type=" + type +
                ", customer=" + customer +
                ", products=" + products +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doc doc = (Doc) o;
        return Objects.equals(id, doc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
