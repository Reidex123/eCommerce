package com.API.eCommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.API.eCommerce.model.Product;

public interface productRepository extends JpaRepository<Product, Long>{

}
