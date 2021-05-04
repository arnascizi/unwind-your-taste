package lt.insoft.arnas.cizikovas.galery.zkgaleryproject.service;

import lt.insoft.arnas.cizikovas.galery.zkgaleryproject.model.Image;
import lt.insoft.arnas.cizikovas.galery.zkgaleryproject.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private ImageRepository repo;

    @Autowired
    public ImageService(ImageRepository repo) {
        this.repo = repo;
    }

    public List<Image> getAllImages() {
        return repo.findAll();
    }
}
