package lt.insoft.arnas.cizikovas.galery.zkgaleryproject.controller;

import lt.insoft.arnas.cizikovas.galery.zkgaleryproject.model.Image;
import lt.insoft.arnas.cizikovas.galery.zkgaleryproject.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        return new ResponseEntity<List<Image>>(imageService.getAllImages(),
                HttpStatus.OK);
    }
}
