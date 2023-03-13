package pl.it.portfolio.DB;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.it.portfolio.DB.interfaces.IProductDAO;
import pl.it.portfolio.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class ProductDAO extends EntityManager implements IProductDAO {
    protected ProductDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Product> getProductsForMainWebSite() {
        return null;
        //TODO dokończyć
    }

    @Override
    public List<Product> getProductsByParams(String name, String category, String delivery, String state, String localization, Double prize) {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery(
                createQuery(name, category, delivery, state, localization, prize),
                Product.class);
        System.out.println(createQuery(name, category, delivery, state, localization, prize));
        setQueryParameters(query, name, category, delivery, state, localization, prize);
        List<Product> productList = query.getResultList();
        session.close();
        return productList;
    }

    @Override
    public void persistProduct(Product product) {
        super.persist(product);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return Optional.empty();
    }


    @Override
    public void updateProduct(Product product) {
      super.update(product);
    }

    @Override
    public void deleteProduct(Product id) {

    }

    public String createQuery(String name, String category, String delivery, String state, String localization, Double prize) {
        StringBuilder queryString = new StringBuilder("FROM pl.it.portfolio.model WHERE ");
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("category", category);
        params.put("delivery", delivery);
        params.put("state", state);
        params.put("localization", localization);
        params.put("prize", String.valueOf(prize));

        for (Map.Entry<String, String> param : params.entrySet()) {

            if (!param.getValue().equals("null")) {
                if (queryString.toString().endsWith("WHERE ")) {
                    queryString.append(param.getKey()).append(" = :").append(param.getKey());
                } else {
                    queryString.append(" and ").append(param.getKey()).append(" ").append(param.getKey());
                }
            }
        }
        return queryString.append(";").toString();
    }

    public void setQueryParameters(Query<Product> query, String name, String category, String delivery, String state,
                                   String localization, Double prize) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("category", category);
        params.put("delivery", delivery);
        params.put("state", state);
        params.put("localization", localization);
        params.put("prize", String.valueOf(prize));
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (!param.getValue().equals("null")) {
                query.setParameter(param.getKey(), param.getValue());
            }
        }
    }
}

//    Session session = this.sessionFactory.openSession();
//    Query<Product> query = session.createQuery(
//            "FROM pl.it.portfolio.model.Product WHERE id = :id",
//            Product.class);
//        query.setParameter("id", id);
//                Optional<Product> result = Optional.empty();
//        try {
//        result = Optional.of(query.getSingleResult());
//        } catch (NoResultException e) {}
//        session.close();
//        return result;