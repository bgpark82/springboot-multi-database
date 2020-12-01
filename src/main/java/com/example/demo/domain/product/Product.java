package com.example.demo.domain.product;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(schema = "products")
public class Product {

    @Id
    private Long id;

    private String name;

    private double price;
}