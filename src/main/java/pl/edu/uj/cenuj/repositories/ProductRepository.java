package pl.edu.uj.cenuj.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.uj.cenuj.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
