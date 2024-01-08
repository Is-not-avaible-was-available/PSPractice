package com.scaler.PSPractice.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private String description;
    private String  image;
    private double price;
    private String category;
}
