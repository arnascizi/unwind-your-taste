package lt.insoft.gallery.bl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.Image;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {

    @Query("SELECT i FROM Image i WHERE i.tags = ?1")
    List<Image> findByTagName(String searchParam);
}
