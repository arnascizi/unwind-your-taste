package lt.insoft.arnas.cizikovas.galery.zkgaleryproject.repository;

import lt.insoft.arnas.cizikovas.galery.zkgaleryproject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
