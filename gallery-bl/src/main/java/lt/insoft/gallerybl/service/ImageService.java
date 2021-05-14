package lt.insoft.gallerybl.service;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallerybl.repository.ImageRepository;
import lt.insoft.gallerymodel.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private ImageRepository imageRepository;

    public List<Image> fetchAllImages() {
        return imageRepository.findAll();
    }

    public Image fetchImage(Long id) {
        return imageRepository.getOne(id);
    }

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public void removeImage(Long id) {
        imageRepository.deleteById(id);
    }
}
