package com.number1.phonetic.model;

import java.util.ArrayList;
import java.util.List;

public class Products {
    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void add(Product product) {
        if(this.products == null) {
            this.products = new ArrayList<Product>();
        }
        this.products.add(product);
    }
}
