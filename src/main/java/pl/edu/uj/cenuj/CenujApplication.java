package pl.edu.uj.cenuj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CenujApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenujApplication.class, args);
    }


}
