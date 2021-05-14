package lt.insoft.gallerybl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallerybl.repository.ImageRepository;
import lt.insoft.gallerymodel.model.Image;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> fetchAllImages() {
        return imageRepository.findAll();
    }

    public Image fetchImage(Long id) {
        return imageRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public void removeImage(Long id) {
        imageRepository.deleteById(id);
    }
}
