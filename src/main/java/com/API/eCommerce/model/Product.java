package com.API.eCommerce.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int quantiry;
    private String description;

    private Category category;
    private List<Image> images;

}
