package pl.edu.uj.cenuj.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.uj.cenuj.model.Product;

import java.util.Optional;

public interface ProductsRepository extends MongoRepository<Product, String> {
    Optional<Product> findByProductCode(long productCode);
}
