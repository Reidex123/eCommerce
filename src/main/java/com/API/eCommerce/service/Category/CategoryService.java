/**
 * CategoryService class provides the implementation of the iCategoryService interface for managing categories in the eCommerce application.
 * It interacts with the categoryRepository to perform CRUD operations on Category entities.
 * @author Koketso
 * @version 1.0
 * @since 2026-07-01
 */

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

    /**
     * Retrieves all categories from the repository.
     * @return a list of all categories
     */
    @Override
    public List<Category> getAllCategories() {
        return CategoryRepository.findAll();
    }

    /**
     * Retrieves a category by its ID from the repository.
     * @param id the ID of the category to retrieve
     * @return the category with the specified ID
     * @throws ResourceNotFoundException if the category with the specified ID does not exist
     */
    @Override
    public Category getCategoryById(Long id) throws ResourceNotFoundException {
        return CategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    /**
     * Retrieves a category by its name from the repository.
     * @param name the name of the category to retrieve
     * @return the category with the specified name
     * @throws ResourceNotFoundException if the category with the specified name does not exist or
     */
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

    /**
     * Deletes a category by its ID from the repository.
     * @param id the ID of the category to delete
     * @throws ResourceNotFoundException if the category with the specified ID does not exist
     */
    @Override
    public void deleteCategory(Long id) throws ResourceNotFoundException {
        if (id != null && CategoryRepository.findById(id) != null) {
            CategoryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
    }

    /**
     * Adds a new category to the repository.
     * @param category the category to add
     * @return the added category
     * @throws AlreadyExistException if a category with the same name already exists
     */
    @Override
    public Category addCategory(Category category) throws AlreadyExistException {
        return Optional.of(category)
                .filter(c -> !CategoryRepository.existsByName(c.getName()))
                .map(CategoryRepository::save)
                .orElseThrow(() -> new AlreadyExistException(
                        "Category with name " + category.getName() + " already exists"));
    }

    /**
     * Updates an existing category in the repository.
     * @param category the category with updated information
     * @param id the ID of the category to update
     * @throws ResourceNotFoundException if the category with the specified ID does not exist
     */
    @Override
    public void updateCategory(Category category, Long id) throws ResourceNotFoundException {
        Category existingCategory = CategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        existingCategory.setName(category.getName());

        CategoryRepository.save(existingCategory);
    }
}
