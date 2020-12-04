package com.example.SpringProject.models;

import java.util.HashMap;

public class ProductRequest {
    int product_id;
    private HashMap<String, String> partsRequired;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public HashMap<String, String> getPartsRequired() {
        return partsRequired;
    }

    public void setPartsRequired(HashMap<String, String> partsRequired) {
        this.partsRequired = partsRequired;
    }

}
