package pl.it.portfolio.DB;

import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.it.portfolio.DB.interfaces.IProductDAO;
import pl.it.portfolio.model.Order;
import pl.it.portfolio.model.Product;
import pl.it.portfolio.session.SessionObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Repository
public class ProductDAO extends EntityManager implements IProductDAO {
    @Autowired
    SessionObject sessionObject;
    protected ProductDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Product> listProductsForMainWebSite() {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery(
                "FROM pl.it.portfolio.model.Product WHERE id BETWEEN :startId AND :endId",
                Product.class
        );
        query.setParameter("startId", 1);
        query.setParameter("endId", 10);

        List<Product> products = query.getResultList();
        session.close();
        return products;
    }

    @Override
    public void addProduct(HttpServletRequest request) {

        String name = Arrays.toString((request.getParameterValues("name"))).replaceAll("[^a-z\sA-Z0-9]", "");
        String description = Arrays.toString((request.getParameterValues("description"))).replaceAll("[^a-z\sA-Z0-9]", "");
        String category = Arrays.toString(request.getParameterValues("category")).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        String delivery = Arrays.toString(request.getParameterValues("delivery")).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        String state = Arrays.toString(request.getParameterValues("state")).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        String localization = Arrays.toString(request.getParameterValues("localization")).replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
        int quantity = Integer.parseInt(Arrays.toString(request.getParameterValues("quantity")).replaceAll("[^a-zA-Z0-9]", ""));
        Double prize = Double.parseDouble(Arrays.toString(request.getParameterValues("prize")).
                replaceAll("[^\\d,]+", "").
                replace(",", "."));

        System.out.println((name + " " + category + " " + delivery + " " + state + " " + localization
                + " " + quantity + " " + prize + " " + description));

        String uploadPath = "";
        try {
            Part file = request.getPart("image");
            if (file.toString().equals("image")) {
                uploadPath = "nophoto.jpg";
                System.out.println(uploadPath);
            } else {
                uploadPath = file.getSubmittedFileName();
               System.out.println(uploadPath);
                FileOutputStream fos = new FileOutputStream("C:/Users/Michał/IdeaProjects/Wystaw.pl/src/main/resources/static/uploadedPhotos/" + uploadPath);
                InputStream is = file.getInputStream();
                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        super.persist(new Product(0,this.sessionObject.getUser(), name, prize, quantity, description, Product.State.valueOf(state)
                , Product.Category.valueOf(category), Product.Delivery.valueOf(delivery)
                , Product.Localization.valueOf(localization), uploadPath));
    }

    @Override
    public List<Product> listProductsByParams(String name, String category, String delivery, String state, String localization, Double prize) {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery(
                createQuery(name, category, delivery, state, localization, prize),
                Product.class);
        // System.out.println(createQuery(name, category, delivery, state, localization, prize));
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
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery(
                "FROM pl.it.portfolio.model.Product WHERE id = :id",
                Product.class);
        query.setParameter("id", id);
        Optional<Product> result = Optional.empty();
        try {
            result = Optional.of(query.getSingleResult());
        } catch (NoResultException e) {}
        session.close();
        return result;
    }

    @Override
    public void updateProduct(Product product) {
        super.update(product);
    }

    @Override
    public void deleteProduct(Product id) {
    }

    public String createQuery(String name, String category, String delivery, String state, String localization, Double prize) {
        StringBuilder queryString = new StringBuilder("FROM pl.it.portfolio.model.Product WHERE ");
        Map<String, String> params = new HashMap<>();

        params.put("name", (name == null) ? "" : name);
        params.put("category", (category == null) ? "" : category);
        params.put("delivery", (delivery == null) ? "" : delivery);
        params.put("state", (state == null) ? "" : state);
        params.put("localization", (localization == null) ? "" : localization);
        params.put("prize", (prize == null) ? "" : String.valueOf(prize));

        System.out.println(params);

        for (Map.Entry<String, String> param : params.entrySet()) {
            if (param.getValue() != "") {
                if (queryString.toString().endsWith("WHERE ")) {
                    if (param.getKey().equals("prize")) {
                        queryString.append("prize BETWEEN :startPrize AND :endPrize");
                    }
                    if (!param.getKey().equals("prize") && !param.getKey().equals("name")) {
                        queryString.append(param.getKey()).append(" = :").append(param.getKey());
                    }
                    if (param.getKey().equals("name")) {
                        queryString.append("name LIKE :name");
                    }
                } else {
                    if (!param.getKey().equals("prize") && !param.getKey().equals("name")) {
                        queryString.append(" AND ").append(param.getKey()).append(" = :").append(param.getKey());
                    } else {
                        queryString.append(" AND prize BETWEEN :startPrize AND :endPrize");
                    }
                }
            }
        }
        return queryString.toString();
    }

    public void setQueryParameters(Query<Product> query, String name, String category, String delivery, String state,
                                   String localization, Double prize) {
        Map<String, String> params = new HashMap<>();
        params.put("name", (name == null) ? "" : name);
        params.put("category", (category == null) ? "" : category.toUpperCase());
        params.put("delivery", (delivery == null) ? "" : delivery.toUpperCase());
        params.put("state", (state == null) ? "" : state.toUpperCase());
        params.put("localization", (localization == null) ? "" : localization.toUpperCase());
        params.put("prize", (prize == null) ? "" : String.valueOf(prize));

        for (Map.Entry<String, String> param : params.entrySet()) {
            if (!param.getValue().equals("")) {
                if (param.getKey().equals("prize")) {
                    query.setParameter("startPrize", 0);
                    query.setParameter("endPrize", prize);
                }
                if (param.getKey().equals("name")) {
                    query.setParameter("name", "%" + name + "%");
                }
                if (param.getKey().equals("category")) {
                    query.setParameter(param.getKey(), Product.Category.valueOf(param.getValue()));
                }
                if (param.getKey().equals("delivery")) {
                    query.setParameter(param.getKey(), Product.Delivery.valueOf(param.getValue()));
                }
                if (param.getKey().equals("state")) {
                    query.setParameter(param.getKey(), Product.State.valueOf(param.getValue()));
                }
                if (param.getKey().equals("localization")) {
                    query.setParameter(param.getKey(), Product.Localization.valueOf(param.getValue()));
                }
            }
        }
    }
}
