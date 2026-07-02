package com.API.eCommerce.service.Category;

import com.API.eCommerce.Repository.categoryRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.API.eCommerce.model.Category;
import java.util.List;
import com.API.eCommerce.Exceptions.ResourceNotFoundException;
import java.util.Optional;
import com.API.eCommerce.Exceptions.AlreadyExistException;

@Service
@RequiredArgsConstructor
public class CategoryService implements iCategoryService {

    private final categoryRepository CategoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return CategoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) throws ResourceNotFoundException {
        return CategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    public Category getCategoryByName(String name) throws ResourceNotFoundException {
        if (name != null) {
            Category category = CategoryRepository.findByName(name);
            if (category != null) {
                return category;
            } else {
                throw new ResourceNotFoundException("Category not found with name: " + name);
            }
        } else {
            throw new ResourceNotFoundException("Category name cannot be null");
        }
    }

    @Override
    public void deleteCategory(Long id) throws ResourceNotFoundException {
        if (id != null && CategoryRepository.findById(id) != null) {
            CategoryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
    }

    @Override
    public Category addCategory(Category category) throws AlreadyExistException {
        return Optional.of(category)
                .filter(c -> !CategoryRepository.existsByName(c.getName()))
                .map(CategoryRepository::save)
                .orElseThrow(() -> new AlreadyExistException(
                        "Category with name " + category.getName() + " already exists"));
    }

    @Override
    public void updateCategory(Category category, Long id) {
    }
}
