package com.API.eCommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.eCommerce.model.Image;

public interface  imageRepository extends JpaRepository<Image, Long> {

}
