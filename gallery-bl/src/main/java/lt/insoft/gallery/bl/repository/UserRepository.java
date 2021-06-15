package lt.insoft.gallery.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUsername(String username);
}
