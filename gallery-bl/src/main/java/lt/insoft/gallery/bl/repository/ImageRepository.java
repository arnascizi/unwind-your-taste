package lt.insoft.gallery.bl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.Image;
import lt.insoft.gallery.model.Tag;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT i FROM Image i WHERE i.tags = ?1")
    List<Image> findByTagName(String searchParam);

    @Query("SELECT i, t From Image i INNER JOIN Tag t ON i.id = t.id")
    List<Tag> findAllTagsByImageId(Long id);
}
