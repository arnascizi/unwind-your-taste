package lt.insoft.gallery.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.exception.ImageNotFoundException;
import lt.insoft.gallery.bl.repository.ImageRepository;
import lt.insoft.gallery.model.Image;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService{

    private final ImageRepository imageRepository;

    public List<Image> fetchAllImages() {
        return imageRepository.findAll();
    }

    public Image fetchImage(Long id) throws ImageNotFoundException {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
    }

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public void removeImage(Long id) throws ImageNotFoundException {
        Image image = fetchImage(id);
        imageRepository.delete(image);
    }
}
