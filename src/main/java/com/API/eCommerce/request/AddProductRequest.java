package com.API.eCommerce.request;

import java.math.BigDecimal;
import com.API.eCommerce.model.Category;
import lombok.Data;

@Data
public class AddProductRequest {
    private String name;
    private String brand;
    private BigDecimal price;
    private int quantiry;
    private String description;
    private Category category;

}
