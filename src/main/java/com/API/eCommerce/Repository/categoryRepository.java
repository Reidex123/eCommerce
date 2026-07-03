/**
 * This is the categoryRepository interface that extends JpaRepository to provide CRUD operations for Category entities.
 * It includes methods to find a category by its name and to check if a category with a specific name already exists in the database.
 * @author Koketso
 * @version 1.0
 * @since 2026-07-01
 */

package com.API.eCommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.eCommerce.model.Category;

public interface categoryRepository extends JpaRepository<Category, Long> {

    /**
     * Finds a category by its name.
     * @param name the name of the category to find
     * @return the category with the specified name, or null if no such category exists
     */
    Category findByName(String name);

    /**
     * Checks if a category with the specified name already exists in the database.
     * @param name the name of the category to check
     * @return true if a category with the specified name exists, false otherwise
     */
    boolean existsByName(String name);
}
