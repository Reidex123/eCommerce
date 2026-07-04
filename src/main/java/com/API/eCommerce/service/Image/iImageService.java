/**
 * This interface defines the contract for the ImageService, which provides methods for managing images in the eCommerce application.
 * It includes methods for retrieving, saving, updating, and deleting images associated with products.
 * @author Koketso
 * @since 2026-07-03
 * @version 1.0
 */

package com.API.eCommerce.service.Image;
import org.springframework.web.multipart.MultipartFile;
import com.API.eCommerce.model.Image;
import java.util.List;
import com.API.eCommerce.DTOs.ImageDTO;

public interface iImageService {

    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDTO> saveImage(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
