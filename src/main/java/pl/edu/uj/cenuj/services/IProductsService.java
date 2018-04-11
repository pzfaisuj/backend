package pl.edu.uj.cenuj.services;

import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.model.Product;


import java.util.List;

public interface IProductsService {
    Product getItemById(String id) throws ProductNotFoundException;

    List<Product> getAll();

    String add(Product product) throws ProductExistException;

    Product change(Product product) throws ProductNotFoundException;

    void delete(String id) throws ProductNotFoundException;
}
