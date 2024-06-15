package lk.quontacom.task.ers.model.Repository;

import lk.quontacom.task.ers.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , String> {
    Optional<Employee> findById(String id);
    boolean existsById(String id);

}
