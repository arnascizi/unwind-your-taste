package lt.insoft.gallery.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.insoft.gallery.model.ImageOld;

public interface ImageRepository extends JpaRepository<ImageOld, Long> {}
