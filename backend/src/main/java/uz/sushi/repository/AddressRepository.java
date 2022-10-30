package uz.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sushi.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
