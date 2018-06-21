package pl.edu.uj.cenuj.services;

import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.UnknownDomainForLinkException;
import pl.edu.uj.cenuj.model.ProductLink;
import pl.edu.uj.cenuj.model.frontend.ProductWithLinks;

import java.util.List;

public interface IProductLinkService {
    List<ProductLink> prepareProductLinks(ProductWithLinks product) throws UnknownDomainForLinkException;

    void add(List<ProductLink> productLinks) throws ProductExistException;
    String add(ProductLink productLink) throws ProductExistException;
    List<ProductLink> getLinksByProductCode(long productCode);
}
