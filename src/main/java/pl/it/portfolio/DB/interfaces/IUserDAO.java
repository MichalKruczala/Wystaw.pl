package pl.it.portfolio.DB.interfaces;

import pl.it.portfolio.exceptions.UserLoginExistException;
import pl.it.portfolio.model.User;

import java.util.Optional;

public interface IUserDAO {

    Optional<User> findUserByLogin(String login);
    void saveUser(User user) throws UserLoginExistException;
    String getThatLoginExists();
    void setThatLoginExists(String thatLoginExists);
    void makeUserToModerator(User user);
    void makeUserToAdmin(User user);



}
