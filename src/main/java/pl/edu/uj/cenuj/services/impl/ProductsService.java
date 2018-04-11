package pl.edu.uj.cenuj.services.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.model.Product;
import pl.edu.uj.cenuj.repositories.ProductsRepository;
import pl.edu.uj.cenuj.services.IProductsService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService implements IProductsService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
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
    public List<Product> getAll() {
        return productsRepository.findAll();
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
}
