package com.API.eCommerce.service.Product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.API.eCommerce.Repository.productRepository;
import com.API.eCommerce.model.Product;

@Service
public class ProductService implements iProductService {
    productRepository ProductRepository;

    public ProductService(productRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @Override
    public List<Product> getAllProducts() {

        return this.ProductRepository.findAll();
    }
}
