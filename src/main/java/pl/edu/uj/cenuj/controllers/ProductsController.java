package pl.edu.uj.cenuj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.model.Product;
import pl.edu.uj.cenuj.services.IProductsService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductsController {
    private final IProductsService productsService;

    @Autowired
    public ProductsController(IProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) throws ProductNotFoundException {
        return productsService.getItemById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productsService.getAll();
    }

    @PostMapping("/products")
    public ResponseEntity<Resource<String>> addProduct(@RequestBody @Valid Product product) throws ProductExistException {
        String id = productsService.add(product);
        Resource<String> resource = new Resource<>(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @PutMapping("/products")
    public ResponseEntity<Product> modifyProduct(@RequestBody Product product) throws ProductNotFoundException {
        Product changedProduct = productsService.change(product);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(changedProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id) throws ProductNotFoundException {
        productsService.delete(id);
    }

}
