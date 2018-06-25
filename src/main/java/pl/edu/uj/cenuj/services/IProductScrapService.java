package pl.edu.uj.cenuj.services;

import pl.edu.uj.cenuj.model.scrapping.ScrapData;
import pl.edu.uj.cenuj.model.scrapping.ScrappedProductLinks;

import java.util.List;

public interface IProductScrapService {
    List<ScrapData> getProductsToScrap();

    void addProductsHistory(ScrappedProductLinks scrappedProductLink);
}
