package pl.edu.uj.cenuj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uj.cenuj.exceptions.InvalidDateFormatException;
import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.model.ProductHistory;
import pl.edu.uj.cenuj.services.IProductsHistoryService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class ProductsHistoryController {
    private final IProductsHistoryService productsHistoryService;

    @Autowired
    public ProductsHistoryController(IProductsHistoryService productsHistoryService) {
        this.productsHistoryService = productsHistoryService;
    }

    @GetMapping("/products-history/{id}")
    public ProductHistory getProductHistoryById(@PathVariable String id) throws ProductNotFoundException {
        return productsHistoryService.getItemById(id);
    }

    @GetMapping("/products-history/product/{productId}")
    public List<ProductHistory> getProductHistoryByProductId(@PathVariable String productId) throws ProductNotFoundException {
        return productsHistoryService.getByProductId(productId);
    }

    @GetMapping("/products-history")
    public List<ProductHistory> getAllProductsHistory() {
        return productsHistoryService.getAll();
    }

    @GetMapping("/products-history/{id}/day/{date}")
    public List<ProductHistory> getProductsHistoryHistoryFromDay(@PathVariable String id, @PathVariable String date) throws InvalidDateFormatException {
        return productsHistoryService.getAllFromDay(id, date);
    }

    @PostMapping("/products-history")
    public ResponseEntity<String> addProductHistory(@RequestBody @Valid ProductHistory product) throws ProductExistException, ProductNotFoundException {
        String id = productsHistoryService.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping("/products-history")
    public ResponseEntity<ProductHistory> modifyProductHistory(@RequestBody ProductHistory product) throws ProductNotFoundException {
        ProductHistory changedProduct = productsHistoryService.change(product);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changedProduct);
    }

    @DeleteMapping("/products-history/{id}")
    public void deleteProductHistory(@PathVariable String id) throws ProductNotFoundException {
        productsHistoryService.delete(id);
    }
}
