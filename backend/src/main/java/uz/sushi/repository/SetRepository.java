package uz.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sushi.entity.SetOfProduct;

public interface SetRepository extends JpaRepository<SetOfProduct, Integer> {

}
