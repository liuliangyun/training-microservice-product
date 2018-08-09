package com.tw.microservice.product.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private String unit;
    private String imag;

    public Product(String name, double price, String unit, String imag) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.imag = imag;
    }

    public Product() {
    }
}
