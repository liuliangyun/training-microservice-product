package com.tw.microservice.product.service;

import com.tw.microservice.product.entity.Product;
import com.tw.microservice.product.exception.ProductNotFoundException;
import com.tw.microservice.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Product get(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) throws ProductNotFoundException {
        Product selectedProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        selectedProduct.setName(product.getName());
        selectedProduct.setPrice(product.getPrice());
        selectedProduct.setUnit(product.getUnit());
        selectedProduct.setUnit(product.getImag());
        productRepository.save(selectedProduct);
        return selectedProduct;
    }

    public void remove(Long id) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(id);
    }

}
