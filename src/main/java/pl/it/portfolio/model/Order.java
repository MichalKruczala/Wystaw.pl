package pl.it.portfolio.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "torder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderPosition> orderPosition = new LinkedList<>();
    @ManyToOne
    User user;

    public Order(int id, List<OrderPosition> orderPosition, User user, Status state, int total, LocalDateTime date) {
        this.id = id;
        this.orderPosition =orderPosition;
        this.user = user;
        this.state = state;
        this.total = total;
        this.date = date;
    }

    public Order() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderPosition> getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(List<OrderPosition> orderPosition) {
        this.orderPosition = orderPosition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private Status state;
    private int total;
    private LocalDateTime date;

    public enum Status {
        NEW,
        PACKED,
        ONTHEWAY,
        DELIVERED;

    }
}
