package pl.edu.uj.cenuj.model.scrapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrappedProductLinks {

    private String productLinkId;
    private Double price;
    private Timestamp timestamp;
}
