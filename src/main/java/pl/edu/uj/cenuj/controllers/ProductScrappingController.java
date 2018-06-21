package pl.edu.uj.cenuj.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.uj.cenuj.model.scrapping.ScrapData;
import pl.edu.uj.cenuj.model.scrapping.ScrappedProductLinks;
import pl.edu.uj.cenuj.services.IProductScrapService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class ProductScrappingController {
    private final IProductScrapService productScrapService;

    @Autowired
    public ProductScrappingController(IProductScrapService productScrapService) {
        this.productScrapService = productScrapService;
    }

    @GetMapping("/scrap")
    public List<ScrapData> getProductHistoryByProductId() {
        final List<ScrapData> productsToScrap = productScrapService.getProductsToScrap();
        log.info("getting products to scrap, got: {} domains", productsToScrap);
        return productsToScrap;
    }

    @PostMapping("/scrap")
    public ResponseEntity<String> addNewProducts(@RequestBody @Valid List<ScrappedProductLinks> scrappedProductLinks) {
        log.info("got products to scrap");
        productScrapService.addProductsHistory(scrappedProductLinks);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("product history will be processed");
    }
}
