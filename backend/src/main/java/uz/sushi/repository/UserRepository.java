package uz.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.sushi.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndPasswordIsAndEnabledIsTrue(String email, String password);

    User findByEmail(String email);

    @Query(nativeQuery = true,
    value = "ALTER TABLE users ADD name varchar ;")
    void executeQuery();
}
