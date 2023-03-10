package pl.it.portfolio.model;

import jakarta.persistence.*;
import pl.it.portfolio.model.interfaces.Saveable;

@Entity
public class OrderPosition implements Saveable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Product product;
    int quantity;

    public OrderPosition(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
    public OrderPosition(Product product,int quantity){
        this.product=product;
        this.quantity=quantity;
    }

    public OrderPosition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
