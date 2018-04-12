package pl.edu.uj.cenuj.exceptions;

public class ProductNotFoundException extends Exception {
private static final String DEFAULT_MESSAGE = "Product cannot be found!";

public static ProductNotFoundException withDefaultMessage() {
        return new ProductNotFoundException(DEFAULT_MESSAGE);
        }
public ProductNotFoundException(String message) {
        super(message);
        }
        }
