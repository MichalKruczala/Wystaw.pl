package pl.it.portfolio.services;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.it.portfolio.DB.interfaces.IUserDAO;
import pl.it.portfolio.exceptions.UserLoginExistException;
import pl.it.portfolio.model.User;
import pl.it.portfolio.services.interfaces.IAuthencitationService;
import pl.it.portfolio.session.SessionObject;

import java.util.Optional;

@Service
public class Authenticationervices implements IAuthencitationService {
    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.userDAO.findUserByLogin(login);
        if (userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setUser(     //to ustawiamy typka w sesji
                    new User.UserBuilder()
                            .clone(userBox.get())
                            .password(null)
                            .build()
            );
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setUser(null);
    }

    @Override
    public void registerUser(User user) throws UserLoginExistException {
        user.setRole(User.Role.USER);
        this.userDAO.saveUser(user);
    }


}
