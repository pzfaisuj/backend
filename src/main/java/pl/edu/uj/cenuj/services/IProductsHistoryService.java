package pl.edu.uj.cenuj.services;

import pl.edu.uj.cenuj.exceptions.InvalidDateFormatException;
import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.model.ProductHistory;

import java.util.List;

public interface IProductsHistoryService {
    ProductHistory getItemById(String id) throws ProductNotFoundException;

    List<ProductHistory> getAll();

    String add(ProductHistory productHistory) throws ProductExistException, ProductNotFoundException;

    ProductHistory change(ProductHistory productHistory) throws ProductNotFoundException;

    void delete(String id) throws ProductNotFoundException;

    List<ProductHistory> getAllFromDay(String id, String day) throws InvalidDateFormatException;
}
