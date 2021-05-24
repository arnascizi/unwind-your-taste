package lt.insoft.gallery.bl.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.repository.UserRepository;
import lt.insoft.gallery.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save(UserAccount userAccount) {
        userRepository.save(userAccount);
    }

    public UserAccount getUser(String username) {
        return userRepository.findByUsername(username);
    }
}
