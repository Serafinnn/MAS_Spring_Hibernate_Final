package mas.Repositories;

import mas.Models.Framework;
import mas.Models.Initiative;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InitiativeRepository extends CrudRepository<Initiative, Long> {

    Initiative findByName(String name);
}