/**
 * This interface defines the contract for the ProductService, which provides various operations
 * related to products in the eCommerce application.
 * @author Koketso
 * @version 1.0
 * @since 2026-06-20
 */

package com.API.eCommerce.service.Product;

import java.util.List;

import com.API.eCommerce.model.Product;

public interface iProductService {

    /**
     * Adds a new product to the system.
     * @param product the product to be added
     */
    Product addProduct(Product product);

    /**
     * Retrieves a product by its ID.
     * @param id the ID of the product to retrieve
     * @return the product with the specified ID
     */
    Product getProductById(Long id);

    /**
     * Deletes a product by its ID.
     * @param id the ID of the product to delete
     */
    void deleteProductById(Long id);

    /**
     * Updates an existing product by its ID.
     * @param product the updated product information
     * @param productId the ID of the product to update
     */
    void updateProductById(Product product, Long productId);

    /**
     * Retrieves all products from the system.
     * @return a list of all products
     */
    List<Product> getAllProducts();

    /**
     * Retrieves products by their category.
     * @param category the category of the products to retrieve
     * @return a list of products in the specified category
     */
    List<Product> getProductsByCategory(String category);

    /**
     * Retrieves products by their brand.
     * @param brand the brand of the products to retrieve
     * @return a list of products of the specified brand
     */
    List<Product> getProductByBrand(String brand);

    /**
     * Retrieves products by their brand and category.
     * @param brand the brand of the products to retrieve
     * @param category the category of the products to retrieve
     * @return a list of products of the specified brand and category
     */
    List<Product> getProductByBrandAndCategory(String brand, String category);

    /**
     * Retrieves products by their brand and name.
     * @param brand the brand of the products to retrieve
     * @param name the name of the products to retrieve
     * @return a list of products of the specified brand and name
     */
    List<Product> getProductByBrandAndName(String brand, String name);

    /**
     * Retrieves products by their name.
     * @param name the name of the products to retrieve
     * @return a list of products with the specified name
     */
    List<Product> getProductByName(String name);

    /**
     * Counts the number of products by their brand and name.
     * @param brand the brand of the products to count
     * @param name the name of the products to count
     * @return the count of products with the specified brand and name
     */
    Long countProductByBrandAndName(String brand, String name);
}
