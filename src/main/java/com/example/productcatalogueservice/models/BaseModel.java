package com.example.productcatalogueservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@lombok.Getter
@lombok.Setter

//this will not create table for base model and add given fields in tables for the child classes.
@MappedSuperclass
public abstract class BaseModel {
    @Id
    Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
