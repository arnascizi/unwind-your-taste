package lt.insoft.gallery.bl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    void deleteByName(String name);

    Tag getTagByNameContaining(String name);

    List<Tag> getTagsByNameContainingIgnoreCase(String name);
}
