package lk.quontacom.task.model.Repository;

import lk.quontacom.task.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , String> {
    Employee findByEmployee_id(String id);
    boolean existsByEmployee_id(String id);

}
