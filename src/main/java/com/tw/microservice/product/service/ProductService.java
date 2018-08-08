package com.tw.microservice.product.service;

import com.tw.microservice.product.entity.Product;
import com.tw.microservice.product.exception.ProductNotFoundException;
import com.tw.microservice.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product update(int id, Product product) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setId(id);
        return productRepository.save(product);
    }


    public void remove(int id) {
        productRepository.deleteById(id);
    }

    public Product get(int id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

    }
}
