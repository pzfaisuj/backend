package pl.edu.uj.cenuj.model;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class Product {
    @Id
    private String id;
    @NotEmpty
    private String name;
    private Date creationDate;
    private String description;

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }
}