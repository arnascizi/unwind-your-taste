package lt.insoft.gallery.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.repository.ImageRepository;
import lt.insoft.gallery.model.ImageOld;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public List<ImageOld> fetchAllImages() {
        return imageRepository.findAll();
    }

    public ImageOld fetchImage(Long id) throws NotFoundException {
        return imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Such image with id " + id +" is not found!"));
    }

    public void saveImage(ImageOld image) {
        imageRepository.save(image);
    }

    public void removeImage(Long id) {
        imageRepository.deleteById(id);
    }
}
