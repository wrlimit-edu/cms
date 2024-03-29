package edu.khatypov.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class ProductDiscount {
    @Id
    private String id;
    private String name;
    private int value;
    private boolean enabled;

    public ProductDiscount() {
    }

    public ProductDiscount(String name, int value, boolean enabled) {
        this.name = name;
        this.value = value;
        this.enabled = enabled;
    }

    public ProductDiscount(String id, String name, int value, boolean enabled) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getLongName() {
        return name + " (" + value + "%)";
    }

    @Override
    public String toString() {
        return "ProductDiscount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDiscount that = (ProductDiscount) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
