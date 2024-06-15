package lk.quontacom.task.model.Repository;

import lk.quontacom.task.model.entity.Employee;
import lk.quontacom.task.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUser_id(String id);
    boolean existsByUser_id(String id);
}
