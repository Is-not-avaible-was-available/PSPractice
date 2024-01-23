package com.scaler.PSPractice.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> products;
}
