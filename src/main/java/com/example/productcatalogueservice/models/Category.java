package com.example.productcatalogueservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@lombok.Getter
@lombok.Setter
@Entity
public class Category extends BaseModel {
    String name;
    String description;
    @OneToMany(mappedBy = "category")
    List<Product> products;
}
