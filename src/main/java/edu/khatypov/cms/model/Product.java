package edu.khatypov.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.text.*;

@Document
public class Product {
    @Id
    private String id;
    private int number;
    private String name;
    private String description;
    private float price;
    private ProductDiscount productDiscount;
    private int amount;

    public Product() {
    }

    public Product(int number, String name, String description, float price, ProductDiscount productDiscount, int amount) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productDiscount = productDiscount;
        this.amount = amount;
    }

    public Product(String id, int number, String name, String description, float price, ProductDiscount productDiscount, int amount) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productDiscount = productDiscount;
        this.amount = amount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ProductDiscount getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(ProductDiscount productDiscount) {
        this.productDiscount = productDiscount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getDiscountPrice() {
        return (float) Math.round(price * 100 - price * productDiscount.getValue()) / 100;
    }

    public String getFullPriceString() {
        String priceStr;
        if (this.getDiscountPrice() != price) {
            priceStr = "<s>" + new DecimalFormat("0.00").format(price) + " грн.</s> <span class='text-danger'>"
                    + new DecimalFormat("0.00").format(this.getDiscountPrice()) + " грн.</span>";
        } else {
            priceStr = new DecimalFormat("0.00").format(price) + " грн.";
        }
        return priceStr;
    }

    public float getSum() {
        return getDiscountPrice() * amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productDiscount=" + productDiscount +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
