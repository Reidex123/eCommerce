/**
 * This is the ImageService class that implements the iImageService interface. It provides methods for managing images in the eCommerce application,
 * including retrieving, saving, updating, and deleting images associated with products.
 * The service interacts with the imageRepository for database operations and the ProductService to associate images with products.
 * @author Koketso
 * @since 2026-07-03
 * @version 1.0
 */

package com.API.eCommerce.service.Image;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.API.eCommerce.DTOs.ImageDTO;
import com.API.eCommerce.Repository.imageRepository;
import com.API.eCommerce.model.Image;
import com.API.eCommerce.model.Product;
import com.API.eCommerce.service.Product.ProductService;
import com.API.eCommerce.Exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements iImageService {

    private final imageRepository imageRepository;
    private final ProductService productService;

    /**
     * Retrieves an image by its ID.
     * @param id The ID of the image to retrieve.
     * @return The Image object corresponding to the provided ID.
     * @throws ResourceNotFoundException if the image with the specified ID does not exist.
     */
    @Override
    public Image getImageById(Long id) throws ResourceNotFoundException{
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + id));
    }

    /**
     * Deletes an image by its ID.
     * @param id The ID of the image to delete.
     * @throws ResourceNotFoundException if the image with the specified id is not found in the DB
     */
    @Override
    public void deleteImageById(Long id) throws ResourceNotFoundException {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("Image not found with id: " + id);
        });
    }

    /**
     * Saves a list of images associated with a specific product.
     * @param files The list of MultipartFile objects representing the images to be saved.
     * @param productId The ID of the product to which the images will be associated.
     * @return A list of ImageDTO objects representing the saved images.
     * @throws RuntimeException if there is an error while saving the images.
     */
    @Override
    public List<ImageDTO> saveImage(List<MultipartFile> files, Long productId) throws RuntimeException {
        Product product = productService.getProductById(productId);

        // Create a list to hold the ImageDTO objects to be returned
        List<ImageDTO> imageDTOs = new ArrayList<>();

        // Iterate through each file and save it as an Image entity
        for (MultipartFile file : files) {
            try {

                // Create a new Image entity and set its properties
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                // Build the download URL for the image and set it in the Image entity
                String buildDownload = "/api/v1/images/image/download/";
                String downloadUrl = buildDownload + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);

                // Update the download URL with the saved image's ID and save it again
                savedImage.setDownloadUrl(buildDownload + savedImage.getId());
                imageRepository.save(savedImage);

                // Create an ImageDTO object to return the saved image's details
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(savedImage.getId());
                imageDTO.setFileName(savedImage.getFileName());
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());

                // Add the ImageDTO to the list of imageDTOs to be returned
                imageDTOs.add(imageDTO);

            } catch (IOException | SQLException e) {
                throw new RuntimeException("Failed to save image", e);
            }
        }

        return imageDTOs;
    }

    /**
     * Updates an existing image with a new file.
     * @param file The new MultipartFile object representing the updated image.
     * @param imageId The ID of the image to be updated.
     * @throws RuntimeException if there is an error while updating the image.
     */
    @Override
    public void updateImage(MultipartFile file, Long imageId) throws RuntimeException {
        Image image = getImageById(imageId);

        try {
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException e | SQLException e) {
            throw new RuntimeException("Failed to update image", e);
        }
    }

}
