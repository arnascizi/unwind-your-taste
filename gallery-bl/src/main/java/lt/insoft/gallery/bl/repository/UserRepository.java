package lt.insoft.gallery.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.insoft.gallery.model.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUsername(String username);
}
