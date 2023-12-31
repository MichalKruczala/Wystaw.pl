package pl.it.portfolio.model;

import jakarta.persistence.*;
import pl.it.portfolio.model.interfaces.Saveable;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "tproduct")
public class Product implements Saveable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    private String name;
    private double prize;
    private int quantity;
    private String description;
    @Enumerated(EnumType.STRING)
    private Localization localization;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Delivery delivery;
    private String photoUrl;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product(int id, User user, String name, double prize, int quantity, String description, State state, Category category, Delivery delivery, Localization localization, String photoUrl) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.prize = prize;
        this.quantity = quantity;
        this.localization = localization;
        this.description = description;
        this.state = state;
        this.delivery = delivery;
        this.category = category;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prize=" + prize +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", localization=" + localization +
                ", state=" + state +
                ", category=" + category +
                ", delivery=" + delivery +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public enum Localization {
        WARSZAWA,
        KRAKOW,
        LODZ,
        POZNAN,
        GDANSK,
        LUBLIN,
        SZCZECIN,
        BYDGOSZCZ,
        KATOWICE,
        BIALYSTOK,
        GDYNIA,
        CZESTOCHOWA,
        RADOM,
        RZESZOW

    }

    public enum Delivery {
        INPOST,
        ABROAD,
        COURIER,
        SELFCOLLECT
    }

    public enum State {
        NEW,
        USED,
        DAMAGED
    }

    public enum Category {
        ELECTRONICS,
        FASHION,
        KID,
        SUPERMARKET,
        HOUSEANDGARDEN,
        BEAUTY,
        HEALTH,
        CULTUREANDENTERTAINMENT,
        SPORTANDTURISM,
        MOTORIZATION,
        PROPERTY,
        COLLECTIONANDART,
        COMPANYANDSERVICES
    }

}
