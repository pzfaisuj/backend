package pl.edu.uj.cenuj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.uj.cenuj.exceptions.UserExistException;
import pl.edu.uj.cenuj.exceptions.UserNotFoundException;
import pl.edu.uj.cenuj.model.User;
import pl.edu.uj.cenuj.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private Optional<User> findUserByName(String name) {
        return repository.getUserByName(name);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = findUserById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("Użytkownik o id: " + id + " nie istnieje");
    }

    public User getUserByName(String name) throws UserNotFoundException {
        Optional<User> user = findUserByName(name);
        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException("Nie znaleziono użytkownika o imieniu: " + name);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Long addUser(User user) throws UserExistException {
        Optional<User> existingUser = findUserByName(user.getName());
        if (existingUser.isPresent()) {
            throw new UserExistException("Użytkownik o imieniu " + user.getName() + " istnieje");
        }
        user = repository.save(user);
        return user.getId();
    }

    public User modifyUser(User user) throws UserNotFoundException {
        deleteUserById(user.getId());
        user = repository.save(user);
        return user;
    }

    private Optional<User> findUserById(Long id) {
        return repository.findById(id);
    }

    private void deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> modifiedUser = findUserById(id);
        if (!modifiedUser.isPresent()) {
            throw new UserNotFoundException("Nie znaleziono użytkownika o id: " + id);
        }
        repository.deleteById(id);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        deleteUserById(id);
    }

}
