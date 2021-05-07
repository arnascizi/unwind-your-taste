package lt.insoft.gallerybl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lt.insoft.gallerymodel.model.Image;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
