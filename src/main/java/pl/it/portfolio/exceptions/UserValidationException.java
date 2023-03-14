package pl.it.portfolio.exceptions;

public class UserValidationException extends Throwable {
    @Override
    public String getMessage() {
        return "Nie udało się zalogować";
    }
}
