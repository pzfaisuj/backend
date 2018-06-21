package pl.edu.uj.cenuj.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import pl.edu.uj.cenuj.model.frontend.ProductWithLinks;

import javax.validation.constraints.NotEmpty;

@Data
public class ProductLink {
    @Id
    private String id;
    private long productCode;
    @NotEmpty
    private String link;
    @NotEmpty
    private String domainName;

    public ProductLink(Product product, String link, Domain domain) {
        productCode = product.getProductCode();
        this.link = link;
        domainName = domain.getName();
    }

    public void generateUniqueId() {
        this.id = (new ObjectId()).toHexString();
    }
}
