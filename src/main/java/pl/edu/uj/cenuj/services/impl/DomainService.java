package pl.edu.uj.cenuj.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.model.Domain;
import pl.edu.uj.cenuj.repositories.DomainRepository;
import pl.edu.uj.cenuj.services.IDomainService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DomainService implements IDomainService {

    private DomainRepository domainRepository;

    @Autowired
    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public Domain add(Domain domain) throws ProductExistException {
        final Optional<Domain> byName = domainRepository.findByName(domain.getName());
        if (byName.isPresent()) {
            throw new ProductExistException(String.format("domain: %s already exists!", domain.getName()));
        }
        return domainRepository.insert(domain);
    }

    @Override
    public void delete(Domain domain) throws ProductNotFoundException {
        final Optional<Domain> byName = domainRepository.findByName(domain.getName());
        if (!byName.isPresent()) {
            throw new ProductNotFoundException(String.format("domain: %s not found!", domain.getName()));
        }
        domainRepository.deleteByName(byName.get().getName());
    }

    @Override
    public List<Domain> getAll() {
        return domainRepository.findAll();
    }
}
