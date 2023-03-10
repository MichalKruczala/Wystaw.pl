package pl.it.portfolio.DB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.it.portfolio.model.interfaces.Saveable;

public abstract class EntityManager {
    public final SessionFactory sessionFactory;

    protected EntityManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(Saveable o) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.persist(o);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Saveable o) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.merge(o);
        session.getTransaction().commit();
        session.close();
    }
}
