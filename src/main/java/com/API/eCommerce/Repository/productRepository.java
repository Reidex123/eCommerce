/**
 * Repository interface for managing Product entities in the eCommerce application.
 * It extends JpaRepository to provide CRUD operations and custom query methods for Product entities.
 * @author Koketso
 * @version 1.0
 * @since 2026-06-20
 */

package com.API.eCommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.API.eCommerce.model.Product;
import java .util.List;

public interface productRepository extends JpaRepository<Product, Long>{

    List<Product> findByCategory(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByBrandAndCategory(String brand, String category);
    List<Product> findByBrandAndName(String brand, String name);
    List<Product> findByName(String name);
    Long countByBrandAndName(String brand, String name);
}
