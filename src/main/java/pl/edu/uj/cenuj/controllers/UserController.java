package pl.edu.uj.cenuj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.uj.cenuj.exceptions.UserExistException;
import pl.edu.uj.cenuj.exceptions.UserNotFoundException;
import pl.edu.uj.cenuj.model.User;
import pl.edu.uj.cenuj.model.UserList;
import pl.edu.uj.cenuj.services.UserService;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping(value = "/users/{name}", headers = "X-API-VERSION=2")
    public User getUserByName(@PathVariable String name) throws UserNotFoundException {
        return service.getUserByName(name);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        return service.getUserById(id);
    }

    @GetMapping("/users")
    public UserList getAllUsers() {
        return new UserList(service.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<Resource<Long>> addUser(@RequestBody @Valid User user) throws UserExistException, UserNotFoundException {
        Long id = service.addUser(user);
        Resource<Long> resource = new Resource<>(id);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUserById(id));
        resource.add(linkTo.withRel("get-user"));

        linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("get-users"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @PutMapping("/users")
    public ResponseEntity<User> modifyUser(@RequestBody User user) throws UserNotFoundException {
        User createdUser = service.modifyUser(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        service.deleteUser(id);
    }

}
