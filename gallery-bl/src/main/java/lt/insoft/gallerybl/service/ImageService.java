package lt.insoft.gallerybl.service;

import lt.insoft.gallerybl.repository.ImageRepository;
import lt.insoft.gallerymodel.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> fetchAllImages() {
        return imageRepository.findAll();
    }
}
