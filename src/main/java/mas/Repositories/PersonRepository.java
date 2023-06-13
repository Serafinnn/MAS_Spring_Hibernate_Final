package mas.Repositories;

import mas.Models.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query(value = "select firstName from Person")
    List<String> getNames();
    @Query(value = "select p.id from Person p where upper(p.firstName) like upper(:name)")
    int findByFirstName(@Param("name") String name);
}
