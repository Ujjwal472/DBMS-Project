package com.example.SpringProject.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PartLogKey implements Serializable {
    private int partId;
    private int day;
    private int month;
    private int year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartLogKey that = (PartLogKey) o;
        return partId == that.partId &&
                day == that.day &&
                month == that.month &&
                year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId, day, month, year);
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
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
