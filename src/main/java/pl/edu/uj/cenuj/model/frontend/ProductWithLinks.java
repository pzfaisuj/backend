package pl.edu.uj.cenuj.model.frontend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.edu.uj.cenuj.model.Product;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductWithLinks extends Product {
    private List<String> links;

        public ProductWithLinks(Product product, List<String> links) {
        super(product.getId(), product.getProductCode(), product.getName(), product.getDescription(), product.getPrice(), product.getImageURL(), product.getCreationDate());
        this.links = links;
    }
}
