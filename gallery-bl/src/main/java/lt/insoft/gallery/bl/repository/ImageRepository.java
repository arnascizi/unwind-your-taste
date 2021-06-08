package lt.insoft.gallery.bl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    // @Query("SELECT i FROM Image i LEFT OUTER JOIN Image_tag it on it.image_tag.image_id = i.id LEFT OUTER JOIN Tag t on it.tag_id = t.id where t.name = ?1")
    // List<Image> findByTagName(String searchParam);

    @Query("SELECT i FROM Image i WHERE i.name = ?1")
    List<Image> findImagesByName(String name);
}
