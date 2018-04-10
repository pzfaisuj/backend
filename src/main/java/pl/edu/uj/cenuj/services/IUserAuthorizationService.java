package pl.edu.uj.cenuj.services;

import java.util.concurrent.ExecutionException;

public interface IUserAuthorizationService {
    void login(String login, String password) throws ExecutionException, InterruptedException;

    void logout(String login, String password);
}
