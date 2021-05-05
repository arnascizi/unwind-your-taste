package lt.insoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.insoft.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
