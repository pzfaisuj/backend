package pl.edu.uj.cenuj.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.cenuj.model.Domain;
import pl.edu.uj.cenuj.model.ProductHistory;
import pl.edu.uj.cenuj.model.ProductLink;
import pl.edu.uj.cenuj.model.scrapping.ProductLinkToScrap;
import pl.edu.uj.cenuj.model.scrapping.ScrapData;
import pl.edu.uj.cenuj.model.scrapping.ScrappedProductLinks;
import pl.edu.uj.cenuj.repositories.DomainRepository;
import pl.edu.uj.cenuj.repositories.ProductLinkRepository;
import pl.edu.uj.cenuj.repositories.ProductsHistoryRepository;
import pl.edu.uj.cenuj.repositories.ProductsRepository;
import pl.edu.uj.cenuj.services.IProductScrapService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductScrapService implements IProductScrapService {
    private final DomainRepository domainRepository;
    private final ProductLinkRepository productLinkRepository;
    private final ProductsRepository productsRepository;
    private final ProductsHistoryRepository productsHistoryRepository;

    @Autowired
    public ProductScrapService(DomainRepository domainRepository, ProductLinkRepository productLinkRepository, ProductsRepository productsRepository, ProductsHistoryRepository productsHistoryRepository) {
        this.domainRepository = domainRepository;
        this.productLinkRepository = productLinkRepository;
        this.productsRepository = productsRepository;
        this.productsHistoryRepository = productsHistoryRepository;
    }

    @Override
    public List<ScrapData> getProductsToScrap() {
        final List<Domain> domains = domainRepository.findAll();

        return domains.stream().map(domain -> {
            final List<ProductLink> allProductsLinks = productLinkRepository.findAllByDomainName(domain.getName());
            final List<ProductLinkToScrap> productLinksToScrap =
                    allProductsLinks.stream()
                            .map(productLink -> new ProductLinkToScrap(productLink.getId(), productLink.getLink()))
                            .collect(Collectors.toList());
            return new ScrapData(domain.getName(), productLinksToScrap);
        }).collect(Collectors.toList());
    }

    @Override
    public void addProductsHistory(List<ScrappedProductLinks> scrappedProductLinks) {
        scrappedProductLinks.forEach(scrappedProductLink ->
                productLinkRepository.findById(scrappedProductLink.getProductLinkId()).ifPresent(productLink ->
                        productsRepository.findByProductCode(productLink.getProductCode()).ifPresent(product -> {
                            final ProductHistory productHistory = new ProductHistory(productLink.getId(), product.getId(), scrappedProductLink.getPrice(), scrappedProductLink.getTimestamp());
                            productsHistoryRepository.save(productHistory);
                        })));
    }
}
