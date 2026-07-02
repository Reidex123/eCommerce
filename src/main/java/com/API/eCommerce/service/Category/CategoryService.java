package com.API.eCommerce.service.Category;

import org.springframework.stereotype.Service;

import com.API.eCommerce.Repository.categoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements iCategoryService {

    private final categoryRepository CategoryRepository;
}
