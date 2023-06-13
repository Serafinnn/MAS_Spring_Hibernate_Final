package mas.Repositories;

import mas.Models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    void removeById(long id);



    Employee findById(int id);

}