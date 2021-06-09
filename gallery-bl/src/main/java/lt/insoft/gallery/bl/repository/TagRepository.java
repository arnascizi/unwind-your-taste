package lt.insoft.gallery.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    void deleteByName(String name);

    @Query("SELECT t FROM Tag t WHERE t.name LIKE %?1%")
    Tag getTagByName(String name);
}
