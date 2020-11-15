package com.example.SpringProject.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductionLogKey implements Serializable {
    private int product_id;
    private int day;
    private int month;
    private int year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionLogKey that = (ProductionLogKey) o;
        return product_id == that.product_id &&
                day == that.day &&
                month == that.month &&
                year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, day, month, year);
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
