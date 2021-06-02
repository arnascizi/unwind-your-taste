package lt.insoft.gallery.bl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.repository.TagRepository;
import lt.insoft.gallery.model.Tag;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    private List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    private List<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
