/**
 * This class represents a Data Transfer Object (DTO) for an image.
 * It contains the necessary fields to transfer image data between different layers of the application.
 * @author Koketso
 * @since 2026-07-03
 * @version 1.0
 */

package com.API.eCommerce.DTOs;
import lombok.Data;
@Data
public class ImageDTO {

    private Long id;
    private String fileName;
    private String downloadUrl;
}
