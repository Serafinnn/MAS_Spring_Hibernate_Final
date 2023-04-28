package mas;

import mas.Models.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"mas.Models"})
public class MasSpringHibernateApplication implements CommandLineRunner {

    @Autowired
    SessionFactory sessionFactory;

    public static void main(String[] args)  {
        SpringApplication.run(MasSpringHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Session session = sessionFactory.openSession();
        List<Country> list = session.createQuery("SELECT cntr from Country cntr", Country.class)
                .getResultList();
        System.out.println(list);
    }
}
