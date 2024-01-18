package com.scaler.PSPractice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String  title;
    private String description;
    private String image;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Price price;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
}
