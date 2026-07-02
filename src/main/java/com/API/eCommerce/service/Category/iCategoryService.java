package com.API.eCommerce.service.Category;

import java.util.List;
import com.API.eCommerce.model.Category;

public interface iCategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    void deleteCategory(Long id);

    Category addCategory(Category category);

    void updateCategory(Category category, Long id);
}
