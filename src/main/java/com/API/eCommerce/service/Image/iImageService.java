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
