package com.example.SpringProject.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class PartUsed {
    @EmbeddedId
    PartUsedKey partUsedKey;
    private int amount;

    public PartUsedKey getPartUsedKey() {
        return partUsedKey;
    }

    public void setPartUsedKey(PartUsedKey partUsedKey) {
        this.partUsedKey = partUsedKey;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
