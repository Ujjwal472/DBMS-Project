package com.example.SpringProject.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PartUsedKey implements Serializable {
    private int part_id;
    private int product_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartUsedKey that = (PartUsedKey) o;
        return part_id == that.part_id &&
                product_id == that.product_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(part_id, product_id);
    }

    public int getPart_id() {
        return part_id;
    }

    public void setPart_id(int part_id) {
        this.part_id = part_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
