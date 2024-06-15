package lk.quontacom.task.ers.model.Repository;

import lk.quontacom.task.ers.model.entity.Employee;
import lk.quontacom.task.ers.model.entity.Role;
import lk.quontacom.task.ers.util.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRole(RoleType role);
    boolean existsById(String id);
}
