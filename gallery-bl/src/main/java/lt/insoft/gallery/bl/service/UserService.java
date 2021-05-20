package lt.insoft.gallery.bl.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.repository.UserRepository;
import lt.insoft.gallery.model.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


}
