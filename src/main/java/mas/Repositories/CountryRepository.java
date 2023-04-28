package mas.Repositories;

import mas.Models.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryRepository{

    @Autowired
    SessionFactory sessionFactory;

    public List<Country> findAll(){
        Session session = sessionFactory.openSession();
        return session.createQuery("SELECT cntr from Country cntr", Country.class)
                .getResultList();
    }
}
