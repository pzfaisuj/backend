package pl.edu.uj.cenuj.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.uj.cenuj.model.Domain;
import pl.edu.uj.cenuj.model.Product;

public interface DomainRepository extends MongoRepository<Domain, String> {
}
