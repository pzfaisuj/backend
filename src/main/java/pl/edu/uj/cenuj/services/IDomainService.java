package pl.edu.uj.cenuj.services;

import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.model.Domain;

import java.util.List;

public interface IDomainService {
    Domain add(Domain domain) throws ProductExistException;

    void delete(Domain domain) throws ProductNotFoundException;

    List<Domain> getAll();
}
