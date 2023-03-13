package pl.it.portfolio.DB;

import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.it.portfolio.DB.interfaces.IProductDAO;
import pl.it.portfolio.model.Product;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Repository
public class ProductDAO extends EntityManager implements IProductDAO {
    protected ProductDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Product> listProductsForMainWebSite() {
        return null;
        //TODO dokończyć
    }

    @Override
    public void addProduct(HttpServletRequest request) {
        String name = Arrays.toString((request.getParameterValues("name"))).replaceAll("[^a-z\sA-Z0-9]", "");
        String category = Arrays.toString(request.getParameterValues("category")).replaceAll("[^a-zA-Z0-9]", "");
        String delivery = Arrays.toString(request.getParameterValues("delivery")).replaceAll("[^a-zA-Z0-9]", "");
        String state = Arrays.toString(request.getParameterValues("state")).replaceAll("[^a-zA-Z0-9]", "");
        String city = Arrays.toString(request.getParameterValues("city")).replaceAll("[^a-zA-Z0-9]", "");
        Double prize = Double.valueOf(Arrays.toString(request.getParameterValues("prize")).
                replaceAll("[^\\d,]+", "").
                replace(",", "."));
        String uploadPath = "";
        try {
            Part file = request.getPart("image");
            String imageFileName = file.getSubmittedFileName();
            uploadPath = "C:/Users/Michał/IdeaProjects/Wystaw.pl/src/main/resources/static/uploadedPhotos/" + imageFileName;
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = file.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name);
        System.out.println(category);
        System.out.println(delivery);
        System.out.println(city);
        System.out.println(state);
        System.out.println(prize);
        System.out.println(uploadPath);
    }

    @Override
    public List<Product> listProductsByParams(String name, String category, String delivery, String state, String localization, Double prize) {
        //  Session session = this.sessionFactory.openSession();
        //  Query<Product> query = session.createQuery(
        //        createQuery(name, category, delivery, state, localization, prize),
        //      Product.class);
        System.out.println(createQuery(name, category, delivery, state, localization, prize));
        //   setQueryParameters(query, name, category, delivery, state, localization, prize);
        //    List<Product> productList = query.getResultList();
        //    session.close();
        //    return productList;
        return new ArrayList<>();
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

        params.put("name", (name == null) ? "" : name);
        params.put("category", (category == null) ? "" : category.toUpperCase());
        params.put("delivery", (delivery == null) ? "" : delivery.toUpperCase());
        params.put("state", (state == null) ? "" : state.toUpperCase());
        params.put("localization", (localization == null) ? "" : localization.toUpperCase());
        params.put("prize", (prize == null) ? "" : String.valueOf(prize));

        System.out.println(params);

        for (Map.Entry<String, String> param : params.entrySet()) {
            if (param.getValue() != "") {
                if (queryString.toString().endsWith("WHERE ")) {
                    if (param.getKey().equals("prize")) {
                        queryString.append("prize BETWEEN :startPrize AND :endPrize");
                    }
                    if (!param.getKey().equals("prize")) {
                        queryString.append(param.getKey()).append(" = :").append(param.getKey());
                    }
                } else {
                    if (!param.getKey().equals("prize")) {
                        queryString.append(" AND ").append(param.getKey()).append(" :").append(param.getKey());
                    } else {
                        queryString.append(" AND prize BETWEEN :startPrize AND :endPrize");
                    }
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