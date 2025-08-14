package com.booleanuk.api.repositories;

import com.booleanuk.api.bagels.Bagel;
import com.booleanuk.api.models.Product;

import java.util.ArrayList;

public class ProductRepository {

    private ArrayList<Product> products;
    private int id;

    public ProductRepository() {
        this.products = new ArrayList<>();

        this.products.add(new Product(this.id++, "Bagel", "Food",  20));
        this.products.add(new Product(this.id ++, "Coffee", "Food", 15));
    }

    public void create(String type, String category, int price) {
        Product product = new Product(this.id ++, type, category, price);
        this.products.add(product);
    }

    public ArrayList<Product> findAll() {
        return this.products;
    }

    public Product find(int id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
