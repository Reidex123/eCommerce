/**
 * This is the ProductService class that implements the iProductService interface.
 * It provides methods for managing products in an e-commerce application, including retrieving, adding, updating, and deleting products.
 * The service interacts with the productRepository to perform database operations and handles exceptions when products are not found.
 * @author Koketso
 * @version 1.0
 * @since 2026-06-20
 */

package com.API.eCommerce.service.Product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.API.eCommerce.Repository.productRepository;
import com.API.eCommerce.model.Product;
import com.API.eCommerce.Exceptions.ProductNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements iProductService {

    private productRepository ProductRepository;

    public ProductService(productRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    /**
     * Retrieves all products from the database.
     * @return a list of all products
     */
    @Override
    public List<Product> getAllProducts() {

        return this.ProductRepository.findAll();
    }

    /**
     * Adds a new product to the database.
     * @param product the product to be added
     * @return the added product
     */
    @Override
    public Product addProduct(Product product) {
        return this.ProductRepository.save(product);
    }

    /**
     * Retrieves a product by its ID.
     * @param id the ID of the product to retrieve
     * @return the product with the specified ID
     * @throws ProductNotFoundException if the product with the specified ID is not found
     */
    @Override
    public Product getProductById(Long id) {
        return this.ProductRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    /**
     * Deletes a product by its ID.
     * @param id the ID of the product to delete
     * @throws ProductNotFoundException if the product with the specified ID is not found
     */
    @Override
    public void deleteProductById(Long id) {
        if (!this.ProductRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        this.ProductRepository.deleteById(id);
    }

    /**
     * Updates an existing product by its ID.
     * @param product the updated product information
     * @param productId the ID of the product to update
     * @throws ProductNotFoundException if the product with the specified ID is not found
     */
    @Override
    public void updateProductById(Product product, Long productId) {
        Product existingProduct = this.ProductRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        existingProduct.setName(product.getName());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());

        this.ProductRepository.save(existingProduct);
    }

    /**
     * Retrieves products by their category.
     * @param category the category of the products to retrieve
     * @return a list of products in the specified category
     */
    @Override
    public List<Product> getProductsByCategory(String category) {

        return this.ProductRepository.findByCategory(category);
    }

    /**
     * Retrieves products by their brand.
     * @param brand the brand of the products to retrieve
     * @return a list of products of the specified brand
     */
    @Override
    public List<Product> getProductByBrand(String brand) {
        return this.ProductRepository.findByBrand(brand);
    }

    /**
     * Retrieves products by their brand and category.
     * @param brand the brand of the products to retrieve
     * @param category the category of the products to retrieve
     * @return a list of products of the specified brand and category
     */
    @Override
    public List<Product> getProductByBrandAndCategory(String brand, String category) {
        return this.ProductRepository.findByBrandAndCategory(brand, category);
    }

    /**
     * Retrieves products by their brand and name.
     * @param brand the brand of the products to retrieve
     * @param name the name of the products to retrieve
     * @return a list of products of the specified brand and name
     */
    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return this.ProductRepository.findByBrandAndName(brand, name);
    }

    /**
     * Retrieves products by their name.
     * @param name the name of the products to retrieve
     * @return a list of products with the specified name
     */
    @Override
    public List<Product> getProductByName(String name) {
        return this.ProductRepository.findByName(name);
    }

    /**
     * Counts the number of products by their brand and name.
     * @param brand the brand of the products to count
     * @param name the name of the products to count
     * @return the count of products with the specified brand and name
     */
    @Override
    public Long countProductByBrandAndName(String brand, String name) {
        return this.ProductRepository.countByBrandAndName(brand, name);
    }
}
