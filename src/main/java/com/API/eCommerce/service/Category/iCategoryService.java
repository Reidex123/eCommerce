/**
 * iCategoryService interface defines the contract for managing categories in the eCommerce application.
 * It declares methods for retrieving, adding, updating, and deleting categories.
 * @author Koketso
 * @version 1.0
 * @since 2026-07-01
 */

package com.API.eCommerce.service.Category;

import java.util.List;

import com.API.eCommerce.model.Category;

public interface iCategoryService {

    /**
     * Retrieves all categories from the repository.
     * @return a list of all categories
     */
    List<Category> getAllCategories();

    /**
     * Retrieves a category by its ID from the repository.
     * @param id the ID of the category to retrieve
     */
    Category getCategoryById(Long id);

    /**
     * Retrieves a category by its name from the repository.
     * @param name the name of the category to retrieve
     * @return the category with the specified name
     */
    Category getCategoryByName(String name);

    /**
     * Deletes a category by its ID from the repository.
     * @param id the ID of the category to delete
     */
    void deleteCategory(Long id);

    /**
     * Adds a new category to the repository.
     * @param category the category to add
     * @return the added category
     */
    Category addCategory(Category category);

    /**
     * Updates an existing category in the repository.
     * @param category the category with updated information
     */
    void updateCategory(Category category, Long id);
}
