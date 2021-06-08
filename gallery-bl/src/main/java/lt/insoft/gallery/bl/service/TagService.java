package lt.insoft.gallery.bl.service;

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

    public void removeTagByName(Tag tag) {
        tagRepository.deleteByName(tag.getName());
    }

    public Tag getTagByName(String tag) {
        return tagRepository.getTagByName(tag);
    }
}
