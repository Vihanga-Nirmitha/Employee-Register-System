package lk.quontacom.task.ers.model.Repository;

import lk.quontacom.task.ers.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findById(String id);
    boolean existsById(String id);
    User findByEmail(String email);
}
