package lt.insoft.gallery.bl.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findImagesByNameContainingIgnoreCase(String name, Pageable pageable);
}
