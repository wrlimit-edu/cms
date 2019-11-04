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

    public Doc() {
    }

    public Doc(int number, LocalDate date, Boolean status, Boolean type, Customer customer, List<Product> products) {
        this.number = number;
        this.date = date;
        this.status = status;
        this.type = type;
        this.customer = customer;
        this.products = products;
    }

    public Doc(String id, int number, LocalDate date, Boolean status, Boolean type, Customer customer, List<Product> products) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.status = status;
        this.type = type;
        this.customer = customer;
        this.products = products;
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

    public int getProductsAmount() {
        int amount = 0;
        if (products != null) {
            for (Product product : products) {
                amount += product.getAmount();
            }
        }
        return amount;
    }

    public float getProductsSum() {
        float sum = 0;
        if (products != null) {
            for (Product product : products) {
                sum += product.getDiscountPrice() * product.getAmount();
            }
        }
        return sum;
    }

    public float getWholesaleDiscount() {
        float discount = 0;
        if (this.getProductsAmount() > 4) {
            discount = (float) Math.round(getProductsSum() * 30) / 100;
        }
        return discount;
    }

    public float getCustomerDiscount() {
        float discount = 0;
        if (customer != null) {
            discount = (float) Math.round((this.getProductsSum() - this.getWholesaleDiscount()) * customer.getCustomerDiscount().getValue()) / 100;
        }
        return discount;
    }

    public float getSum() {
        return this.getProductsSum() - this.getWholesaleDiscount() - this.getCustomerDiscount();
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
