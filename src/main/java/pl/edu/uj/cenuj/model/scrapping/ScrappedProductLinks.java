package pl.edu.uj.cenuj.model.scrapping;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrappedProductLinks {

    private String productLinkId;
    private Double price;
    @JsonFormat(shape=JsonFormat.Shape.NUMBER, pattern="s")
    private Date timestamp;
}
