package com.API.eCommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.API.eCommerce.model.Product;
import java.util.List;
import java.util.Optional;


public interface productRepository extends JpaRepository<Product, Long>{

    @Override
    Optional<Product> findById(Long id);
    List<Product> findByBrand(String brand);
    List<Product> findByName(String name);

    List<Product> findByCategoryAndBrand(String category, String brand);

    List<Product> findByBrandAndName(String brand, String name);

}
