package com.API.eCommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.eCommerce.model.Category;

public interface categoryRepository extends JpaRepository<Category, Long> {

}
