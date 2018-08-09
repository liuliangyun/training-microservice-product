package com.tw.microservice.product.controller;


import com.tw.microservice.product.entity.Product;
import com.tw.microservice.product.exception.ProductNotFoundException;
import com.tw.microservice.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    //获取商品列表
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    //获取一条商品
    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id){
        Product product = productService.get(id);
        return ResponseEntity.ok(product);
    }

    //创建一条商品
    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product) {
        Product productNew = productService.add(product);
        String str = "/products" + String.valueOf(productNew.getId());
        return ResponseEntity.created(URI.create(str)).build();
    }

    //修改一条商品
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        productService.update(id, product);
        return ResponseEntity.noContent().build();
    }

    //删除一条商品
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> remove(@PathVariable Long id){
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void productNotFoundHandle(ProductNotFoundException ex) {

    }

}
