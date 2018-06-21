package pl.edu.uj.cenuj.services.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.exceptions.UnknownDomainForLinkException;
import pl.edu.uj.cenuj.model.Product;
import pl.edu.uj.cenuj.model.ProductLink;
import pl.edu.uj.cenuj.model.frontend.ProductWithLinks;
import pl.edu.uj.cenuj.repositories.DomainRepository;
import pl.edu.uj.cenuj.repositories.ProductsRepository;
import pl.edu.uj.cenuj.services.IProductLinkService;
import pl.edu.uj.cenuj.services.IProductsService;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService implements IProductsService {
    private final ProductsRepository productsRepository;
    private final IProductLinkService productLinkService;

    @Autowired
    public ProductsService(ProductsRepository productsRepository, IProductLinkService productLinkService) {
        this.productsRepository = productsRepository;
        this.productLinkService = productLinkService;
    }

    @Override
    public Product getItemById(@NonNull String id) throws ProductNotFoundException {
        final Optional<Product> productOptional = productsRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw ProductNotFoundException.withDefaultMessage();
    }

    @Override
    public Product getProductWitLinksById(String id) throws ProductNotFoundException {
        final Product product = getItemById(id);
        final List<ProductLink> linksByProductCode = productLinkService.getLinksByProductCode(product.getProductCode());
        final List<@NotEmpty String> stringLinks = linksByProductCode.stream().map(ProductLink::getLink).collect(Collectors.toList());
        return new ProductWithLinks(product, stringLinks);
    }

    @Override
    public List<Product> getAll() {
        return productsRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsWithLinks() {
        final List<Product> all = productsRepository.findAll();
        return all.stream().map(product -> {
            final List<ProductLink> linksByProductCode = productLinkService.getLinksByProductCode(product.getProductCode());
            final List<@NotEmpty String> stringLinks = linksByProductCode.stream().map(ProductLink::getLink).collect(Collectors.toList());
            return new ProductWithLinks(product, stringLinks);
        }).collect(Collectors.toList());
    }

    @Override
    public String add(@NonNull Product product) throws ProductExistException {
        if (product.getId() != null) {
            final boolean exists = productsRepository.existsById(product.getId());
            if (exists) {
                throw new ProductExistException("product already exists!");
            }
        }
        product.generateUniqueId();
        product.setCreationDate(new Date());
        return productsRepository.save(product).getId();
    }

    @Override
    public Product change(@NonNull Product product) throws ProductNotFoundException {
        final boolean exists = productsRepository.existsById(product.getId());
        if (!exists) {
            throw ProductNotFoundException.withDefaultMessage();
        }
        return productsRepository.save(product);
    }

    @Override
    public void delete(@NonNull String id) throws ProductNotFoundException {
        final boolean exists = productsRepository.existsById(id);
        if (!exists) {
            throw ProductNotFoundException.withDefaultMessage();
        }
        productsRepository.deleteById(id);
    }

    @Override
    public String addProductWithLinks(ProductWithLinks product) throws UnknownDomainForLinkException, ProductExistException {
        final List<ProductLink> productLinks = productLinkService.prepareProductLinks(product);
        final String id = add(product);
        productLinkService.add(productLinks);
        return id;
    }


}
