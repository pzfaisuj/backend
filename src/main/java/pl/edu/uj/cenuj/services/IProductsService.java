package pl.edu.uj.cenuj.services;

import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.exceptions.UnknownDomainForLinkException;
import pl.edu.uj.cenuj.model.Product;
import pl.edu.uj.cenuj.model.frontend.ProductWithLinks;


import java.util.List;

public interface IProductsService {
    Product getItemById(String id) throws ProductNotFoundException;

    Product getProductWitLinksById(String id) throws ProductNotFoundException;

    List<Product> getAll();

    List<Product> getAllProductsWithLinks();

    String add(Product product) throws ProductExistException;

    Product change(Product product) throws ProductNotFoundException;

    void delete(String id) throws ProductNotFoundException;

    String addProductWithLinks(ProductWithLinks product) throws ProductNotFoundException, UnknownDomainForLinkException, ProductExistException;
}
