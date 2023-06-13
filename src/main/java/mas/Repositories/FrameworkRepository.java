package mas.Repositories;

import mas.Models.Framework;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface FrameworkRepository extends CrudRepository<Framework, Long> {

    @Query(value = "select f.name from Framework f")
    List<String> getNames();

    Framework findByName(String name);

}
