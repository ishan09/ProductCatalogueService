package com.example.productcatalogueservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@lombok.Getter
@lombok.Setter

@Entity
public class Product extends BaseModel{
    private String name;
    private String description;
    private Double price;
    private long quantity;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    Boolean isPrime;
}
