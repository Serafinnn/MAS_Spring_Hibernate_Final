package mas.Repositories;

import mas.Models.CodeProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CodeProjectRepository extends CrudRepository<CodeProject, Long> {

    @Query(value = "select id from CodeProject where name = :name")
    long findByName(String name);

    int countByName(String name);
}
