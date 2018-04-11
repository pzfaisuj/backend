package pl.edu.uj.cenuj.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.uj.cenuj.model.ProductHistory;

import java.util.Date;
import java.util.List;

public interface ProductsHistoryRepository extends MongoRepository<ProductHistory, String> {
    List<ProductHistory> getProductHistoryByProductIdAndTimestampBetween(String id, Date startDay, Date endDay);
}
