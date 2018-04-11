package pl.edu.uj.cenuj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CenujApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenujApplication.class, args);
    }


}
