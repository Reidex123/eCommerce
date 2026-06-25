package com.API.eCommerce.service.Product;

import org.springframework.stereotype.Service;

import com.API.eCommerce.Repository.productRepository;

@Service
public class ProductService implements iProductService {
    productRepository ProductRepository;
}
