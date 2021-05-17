package lt.insoft.gallery.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.insoft.gallery.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {}
