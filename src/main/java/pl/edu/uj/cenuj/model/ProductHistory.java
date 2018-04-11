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
    private String productId;
    @JsonFormat(shape=JsonFormat.Shape.NUMBER, pattern="s")
    private Date timestamp;
    @NonNull
    private Double price;

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }
}
