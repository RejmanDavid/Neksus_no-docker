package com.example.neksus.services;

import com.example.neksus.dao.ImageDAO;
import com.example.neksus.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImageDAO imageDAO;

    @Autowired
    public ImageService(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    public List<Image> getAllImages() {
        return imageDAO.getAllImages();
    }

    public Image getImageById(Long id) {
        return imageDAO.getImageById(id);
    }

    public List<Image> getImagesByModId(Long modId) {
        return imageDAO.getImagesByModId(modId);
    }

    public boolean addImage(Image image) {
        validateImageData(image);
        return imageDAO.insertImage(image);
    }

    public boolean deleteImage(Long id) {
        return imageDAO.deleteImage(id);
    }

    private void validateImageData(Image image) {
        if (image.getImagePath() == null || image.getImagePath().trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid image path.");
        }

        if (image.getModId() == null || image.getModId() <= 0) {
            throw new IllegalArgumentException("Invalid mod ID.");
        }
    }
}