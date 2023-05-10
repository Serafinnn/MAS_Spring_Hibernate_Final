package mas;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableConfigurationProperties
@EnableTransactionManagement
@EntityScan(basePackages = {"mas.Models"})
public class MasSpringHibernateApplication implements CommandLineRunner {

    @Autowired
    SessionFactory sessionFactory;

    public static void main(String[] args)  {
        SpringApplication.run(MasSpringHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
