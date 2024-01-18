package com.scaler.PSPractice.ThirdParty.FakeStore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
