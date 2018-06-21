package pl.edu.uj.cenuj.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    protected String id;
    protected long productCode;
    @NotEmpty
    protected String name;
    protected String description;
    protected double price;
    protected String imageURL;
    protected Date creationDate;

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }
}