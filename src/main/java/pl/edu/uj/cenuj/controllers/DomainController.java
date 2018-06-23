package pl.edu.uj.cenuj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uj.cenuj.exceptions.ProductExistException;
import pl.edu.uj.cenuj.exceptions.ProductNotFoundException;
import pl.edu.uj.cenuj.exceptions.UnknownDomainForLinkException;
import pl.edu.uj.cenuj.model.Domain;
import pl.edu.uj.cenuj.model.Product;
import pl.edu.uj.cenuj.model.frontend.ProductWithLinks;
import pl.edu.uj.cenuj.services.IDomainService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class DomainController {
    private final IDomainService domainService;

    @Autowired
    public DomainController(IDomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping("/domain")
    public List<Domain> getAllDomains() {
        return domainService.getAll();
    }

    @PostMapping("/domain")
    public ResponseEntity<Resource<String>> addProduct(@RequestBody @Valid Domain domain) throws ProductExistException {
        Domain insertedDomain = domainService.add(domain);
        Resource<String> resource = new Resource<>(insertedDomain.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @DeleteMapping("/domain/{name}")
    public void deleteProduct(@PathVariable String name) throws ProductNotFoundException {
        domainService.delete(new Domain(name));
    }

}
