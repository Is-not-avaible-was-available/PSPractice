package com.scaler.PSPractice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private String id;
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
}
