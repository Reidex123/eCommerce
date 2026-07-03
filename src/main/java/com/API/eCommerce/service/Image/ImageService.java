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

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image not found with id: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("Image not found with id: " + id);
        });
    }

    @Override
    public List<ImageDTO> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);

        List<ImageDTO> imageDTOs = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownload = "/api/v1/images/image/download/";
                String downloadUrl = buildDownload + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);

                savedImage.setDownloadUrl(buildDownload + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(savedImage.getId());
                imageDTO.setFileName(savedImage.getFileName());
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
                imageDTOs.add(imageDTO);

            } catch (IOException | SQLException e) {
                throw new RuntimeException("Failed to save image", e);
            }
        }

        return imageDTOs;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
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
