package pl.edu.uj.cenuj.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class ProductHistory {
    @Id
    private String id;
    @NonNull
    private String productLinkId;
    @NonNull
    private String productId;
    @NonNull
    private Double price;
    @JsonFormat(shape=JsonFormat.Shape.NUMBER, pattern="s")
    private Date timestamp;

    public ProductHistory(String productLinkId, String productId, Double price, Date timestamp) {
        this.productLinkId = productLinkId;
        this.productId = productId;
        this.price = price;
        this.timestamp = timestamp;
    }

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }
}
