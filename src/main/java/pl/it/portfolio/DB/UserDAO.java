package pl.it.portfolio.DB;

import jakarta.persistence.NoResultException;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.it.portfolio.DB.interfaces.IUserDAO;
import pl.it.portfolio.model.User;

import java.util.Optional;

@Repository
public class UserDAO extends EntityManager implements IUserDAO {
    @Override
    public String getThatLoginExists() {
        return thatLoginExists;
    }

    public void setThatLoginExists(String thatLoginExists) {
        UserDAO.thatLoginExists = thatLoginExists;
    }

    private static String thatLoginExists = "";

    public UserDAO(@Autowired SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(
                "FROM pl.it.portfolio.model.User WHERE login =:login", User.class);
        query.setParameter("login", login);
        Optional<User> result = Optional.empty();
        try {
            result = Optional.of(query.getSingleResult());
        } catch (NoResultException ignored) {
        }
        session.close();
        return result;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setRole(User.Role.USER);
        super.persist(user);
    }


    @Override
    public void makeUserToModerator(User user) {
        user.setRole(User.Role.MODERATOR);
    }

    @Override
    public void makeUserToAdmin(User user) {
        user.setRole(User.Role.ADMIN);
    }
}
