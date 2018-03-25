package Spring;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@EnableJpaRepositories("Spring.Repositories")
@ComponentScan("Spring.Repositories")
@EntityScan("Baskets")


public class Application implements CommandLineRunner{
//
//    @Autowired
//    private GoodRepository goodRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
