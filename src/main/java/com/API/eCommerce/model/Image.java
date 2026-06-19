package com.API.eCommerce.model;

import java.sql.Blob;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private Long id;
    private String fileName;
    private String fileType;
    private Blob image;
    private String sourceUrl;
}
