package com.booleanuk.api.controllers;

import com.booleanuk.api.models.Product;
import com.booleanuk.api.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository productRepository;
    private ArrayList<Product> products;

    public ProductController() {
        this.productRepository = new ProductRepository();
        products = new ArrayList<>();
    }

    @GetMapping
    public ArrayList<Product> getAll() {
        return this.productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable int id) {
        Product product = productRepository.find(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found!!!!"); // 404 basically
        }

        return product;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        this.products.add(product);
        return product;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@PathVariable (name = "id") int id, @RequestBody Product product) {
        if (id < this.products.size()) {
            this.products.get(id).setType(product.getType());
            this.products.get(id).setCategory(product.getCategory());
            this.products.get(id).setPrice(product.getPrice());
            return this.products.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable (name = "id") int id) {
        if (id < this.products.size()) {
            return this.products.remove(id);
        }
        return null;
    }
}
