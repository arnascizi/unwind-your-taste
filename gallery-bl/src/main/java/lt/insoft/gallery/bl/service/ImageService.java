package lt.insoft.gallery.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.repository.ImageRepository;
import lt.insoft.gallery.model.Image;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<Image> fetchAllImages() {
        return imageRepository.findAll();
    }

    public Image fetchImage(Long id) throws NotFoundException {
        return imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Such image with id " + id +" is not found!"));
    }

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public void removeImage(Long id) {
        imageRepository.deleteById(id);
    }
}
