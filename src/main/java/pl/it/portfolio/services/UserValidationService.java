package pl.it.portfolio.services;

import org.springframework.stereotype.Service;
import pl.it.portfolio.exceptions.UserValidationException;
import pl.it.portfolio.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class  UserValidationService {
    public void validateUserTryingToLogIn(String login, String password) throws UserValidationException {
        validateLogin(login);
        validatePassword(password);
    }
    public void validateRegisterUser(User user, String repeatedPassword) throws UserValidationException {
        validateName(user.getName());
        validateSurname(user.getSurname());
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validatePasswordsEquality(user.getPassword(), repeatedPassword);
    }

    public static void validateLogin(String login) throws UserValidationException{
        String regex = "^[a-zA-Z0-9]{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(login);
        if (!matcher.matches()) {
            throw new UserValidationException();

        }
    }

    public static void validatePassword(String password)throws UserValidationException {
        String regex = "^[a-zA-Z0-9]{5,}$";
        if (!password.matches(regex)) {
            throw new UserValidationException();

        }
    }

    public static void validateName(String name) throws UserValidationException{
        String regex = "^[A-Z]{1}[a-z]+$";
        if (!name.matches(regex)) {
            throw new UserValidationException();

        }
    }

    public static void validateSurname(String surname)throws UserValidationException {
        String regex = "^[A-Z]{1}[a-z]+(-[A-Z]{1}[a-z]+)?$";
        if (!surname.matches(regex)) {
            throw new UserValidationException();
        }
    }

    public static void validatePasswordsEquality(String password1, String password2)throws UserValidationException {
        if (!password1.equals(password2)) {
            throw new UserValidationException();
        }
    }

}


