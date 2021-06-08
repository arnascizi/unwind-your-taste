package lt.insoft.gallery.bl.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.exception.ImageNotFoundException;
import lt.insoft.gallery.bl.repository.ImageRepository;
import lt.insoft.gallery.bl.repository.TagRepository;
import lt.insoft.gallery.model.Image;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    private final TagRepository tagRepository;

    public List<Image> fetchAllImages() {
        return imageRepository.findAll();
    }

    public Image fetchImage(Long id) throws ImageNotFoundException {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
    }

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public void save(Image image) {
        image.getTags().forEach(tagRepository::save);
        imageRepository.save(image);
    }

    public void removeImage(Long id) throws ImageNotFoundException {
        Image image = fetchImage(id);
        imageRepository.delete(image);
    }

    public List<Image> fetchImagesByTagNames(String searchParam) {
        return null;
    }

    public Iterable<Image> getPageable(Pageable pageable) {
        return imageRepository.findAll(pageable);
    }

    public List<Image> getImagesByName(String name) {
        return imageRepository.findImagesByName(name);
    }
}
