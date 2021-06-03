package lt.insoft.gallery.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {}
