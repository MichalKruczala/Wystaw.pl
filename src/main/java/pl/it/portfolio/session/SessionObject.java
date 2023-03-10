package pl.it.portfolio.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.it.portfolio.model.User;

@Component
@SessionScope
public class SessionObject {
    private int id;
    private String login;
    public User user;

    public User getUser() {
        return user;
    }

    public boolean isLogged() {
        return this.user != null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isModerator() {
        if (!isLogged()) {
            return false;
        }
        return this.user.getRole().equals(User.Role.MODERATOR);
    }

    public boolean isAdmin() {
        if (!isLogged()) {
            return false;
        }
        return this.user.getRole().equals(User.Role.ADMIN);
    }
}
