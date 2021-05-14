package lt.insoft.gallerybl.repository;

import lt.insoft.gallerymodel.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
