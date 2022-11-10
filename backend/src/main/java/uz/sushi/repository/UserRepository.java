package uz.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.sushi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndPasswordIsAndEnabledIsTrue(String email, String password);

    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "ALTER TABLE product_components DROP CONSTRAINT uk_p8ceuvw5b17egmvv2tce56j0p"
    )
    void ketmon();
}
