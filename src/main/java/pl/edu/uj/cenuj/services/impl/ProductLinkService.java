package pl.edu.uj.cenuj.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.UnknownDomainForLinkException;
import pl.edu.uj.cenuj.model.Domain;
import pl.edu.uj.cenuj.model.ProductLink;
import pl.edu.uj.cenuj.model.frontend.ProductWithLinks;
import pl.edu.uj.cenuj.repositories.DomainRepository;
import pl.edu.uj.cenuj.repositories.ProductLinkRepository;
import pl.edu.uj.cenuj.services.IProductLinkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductLinkService implements IProductLinkService {
    private final DomainRepository domainRepository;
    private final ProductLinkRepository productLinkRepository;

    @Autowired
    public ProductLinkService(DomainRepository domainRepository, ProductLinkRepository productLinkRepository) {
        this.domainRepository = domainRepository;
        this.productLinkRepository = productLinkRepository;
    }

    @Override
    public List<ProductLink> prepareProductLinks(ProductWithLinks product) throws UnknownDomainForLinkException {
        final List<ProductLink> productLinksToInsert = new ArrayList<>();
        final List<Domain> allDomains = domainRepository.findAll();
        for (String link: product.getLinks()) {
            final Optional<Domain> domainOptional = allDomains.stream().filter(domain -> link.contains(domain.getName())).findAny();
            if (!domainOptional.isPresent()) {
                throw new UnknownDomainForLinkException(String.format("Domain for link: %s cannot be found. Product won't be inserted", link));
            }
            productLinksToInsert.add(new ProductLink(product, link, domainOptional.get()));
        }
        return productLinksToInsert;
    }

    @Override
    public void add(List<ProductLink> productLinks) throws ProductExistException {
        for (ProductLink productLink : productLinks) {
            add(productLink);
        }
    }

    @Override
    public String add(ProductLink productLink) throws ProductExistException {
        if (productLink.getId() != null) {
            final boolean exists = productLinkRepository.existsById(productLink.getId());
            if (exists) {
                throw new ProductExistException("product link already exists!");
            }
        }
        productLink.generateUniqueId();
        return productLinkRepository.save(productLink).getId();
    }

    @Override
    public List<ProductLink> getLinksByProductCode(long productCode) {
        return productLinkRepository.findAllByProductCode(productCode);
    }
}
