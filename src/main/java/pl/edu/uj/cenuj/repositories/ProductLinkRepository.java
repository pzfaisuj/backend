package pl.edu.uj.cenuj.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.uj.cenuj.model.ProductLink;

import java.util.List;

public interface ProductLinkRepository extends MongoRepository<ProductLink, String> {
    List<ProductLink> findAllByProductCode(long productCode);

    List<ProductLink> findAllByDomainName(String domainName);
}
