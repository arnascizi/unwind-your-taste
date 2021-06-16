package lt.insoft.gallery.bl.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.repository.UserRepository;
import lt.insoft.gallery.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public void save(UserAccount userAccount) {
        userRepository.save(userAccount);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount account = userRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsPrincipal(account);
    }

    public UserAccount findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
