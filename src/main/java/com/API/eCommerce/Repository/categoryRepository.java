package com.API.eCommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import com.API.eCommerce.model.Category;

public interface  categoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
=======

import com.API.eCommerce.model.Category;

public interface categoryRepository extends JpaRepository<Category, Long> {

>>>>>>> main
}
