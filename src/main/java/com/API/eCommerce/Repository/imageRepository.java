/**
 * This interface defines the repository for managing Image entities in the eCommerce application.
 * It extends JpaRepository to provide CRUD operations and additional query methods for Image entities.
 * @author Koketso
 * @since 2026-07-03
 * @version 1.0
 */

package com.API.eCommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.eCommerce.model.Image;

public interface  imageRepository extends JpaRepository<Image, Long> {

}
