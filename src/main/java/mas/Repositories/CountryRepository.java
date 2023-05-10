package mas.Repositories;

import mas.Models.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CountryRepository extends CrudRepository<Country, Long> {
    @Query("""
            select (count(c) > 0) from Country c
            where upper(c.shortName) like upper(:shortName) or upper(c.fullName) like upper(:fullName)""")
    boolean exists(@Param("shortName") @NonNull String shortName, @Param("fullName") @NonNull String fullName);
    List<Country> findCountryByFullNameEndingWith(String ending);

    boolean findByShortNameAndFullName(@NonNull String shortName, @NonNull String fullName);
}
