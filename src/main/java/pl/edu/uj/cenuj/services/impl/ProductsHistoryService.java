package pl.edu.uj.cenuj.services.impl;

import lombok.NonNull;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.cenuj.exceptions.InvalidDateFormatException;
import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.model.ProductHistory;
import pl.edu.uj.cenuj.repositories.ProductsHistoryRepository;
import pl.edu.uj.cenuj.repositories.ProductsRepository;
import pl.edu.uj.cenuj.services.IProductsHistoryService;
import pl.edu.uj.cenuj.utils.DateConstants;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsHistoryService implements IProductsHistoryService {
    private final ProductsHistoryRepository productsHistoryRepository;
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsHistoryService(ProductsHistoryRepository productsHistoryRepository, ProductsRepository productsRepository) {
        this.productsHistoryRepository = productsHistoryRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    public ProductHistory getItemById(String id) throws ProductNotFoundException {
        final Optional<ProductHistory> productOptional = productsHistoryRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw ProductNotFoundException.withDefaultMessage();
    }

    @Override
    public List<ProductHistory> getAll() {
        return productsHistoryRepository.findAll();
    }

    @Override
    public String add(@NonNull ProductHistory productHistory) throws ProductExistException, ProductNotFoundException {
        if (productHistory.getId() != null) {
            final boolean exists = productsHistoryRepository.existsById(productHistory.getId());
            if (exists) {
                throw new ProductExistException("product already exists!");
            }
        }
        final boolean productExists = productsRepository.existsById(productHistory.getProductId());
        if (!productExists) {
            throw ProductNotFoundException.withDefaultMessage();
        }
        productHistory.generateUniqueId();
        return productsHistoryRepository.save(productHistory).getId();
    }

    @Override
    public ProductHistory change(ProductHistory productHistory) throws ProductNotFoundException {
        final boolean exists = productsHistoryRepository.existsById(productHistory.getId());
        if (!exists) {
            throw ProductNotFoundException.withDefaultMessage();
        }
        return productsHistoryRepository.save(productHistory);
    }

    @Override
    public void delete(String id) throws ProductNotFoundException {
        final boolean exists = productsHistoryRepository.existsById(id);
        if (!exists) {
            throw ProductNotFoundException.withDefaultMessage();
        }
        productsHistoryRepository.deleteById(id);
    }

    @Override
    public List<ProductHistory> getAllFromDay(String id, String day) throws InvalidDateFormatException {
        Date startDate;
        try {
            startDate = DateUtils.parseDateStrictly(day, new String[]{DateConstants.DAY_FORMAT});
        } catch (ParseException e) {
            throw InvalidDateFormatException.withInvalidDayMessage();
        }
        return productsHistoryRepository.getProductHistoryByProductIdAndTimestampBetween(id, startDate, DateUtils.addDays(startDate, 1));
    }
}
