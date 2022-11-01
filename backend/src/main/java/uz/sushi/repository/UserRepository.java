package uz.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sushi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndPasswordIsAndEnabledIsTrue(String email, String password);

    User findByEmail(String email);

}
