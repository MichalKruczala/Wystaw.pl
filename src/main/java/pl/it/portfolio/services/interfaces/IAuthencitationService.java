package pl.it.portfolio.services.interfaces;

import pl.it.portfolio.exceptions.UserLoginExistException;
import pl.it.portfolio.model.User;

public interface IAuthencitationService {

    void authenticate(String login, String password);

    void logout();

    void registerUser(User user) throws UserLoginExistException, UserLoginExistException;
}


