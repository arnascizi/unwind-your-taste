package lt.insoft.gallery.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.insoft.gallery.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {}
