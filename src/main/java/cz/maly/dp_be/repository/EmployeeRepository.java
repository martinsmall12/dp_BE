package cz.maly.dp_be.repository;

import cz.maly.dp_be.model.Employee;
import cz.maly.dp_be.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
