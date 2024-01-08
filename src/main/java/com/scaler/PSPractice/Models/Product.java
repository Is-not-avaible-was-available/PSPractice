package com.scaler.PSPractice.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String title;
    private String description;
    private double price;
    private String image;
    private Category category;
}
