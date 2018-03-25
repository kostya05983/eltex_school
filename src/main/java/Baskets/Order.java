package Baskets;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Table(name="Order",schema="public")
public class Order implements Serializable {
    @Column(name="status")
    private boolean status;
    @Column(name="ShoppingCart")
    private ShoppingCart shoppingCart;
    @Column(name="Credentials")
    private Credentials credentials;
    @Column(name="creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Column(name="waiting")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waiting;


    public Order(){
        this.status=false;
        this.shoppingCart=new ShoppingCart();
        this.credentials=new Credentials();
    }

    public String toString(){
        return "статус="+status+" время создания:"+creation+"Время ожидания:"+waiting+"\n"+credentials+"\n"+shoppingCart;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }


    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }


    public Date getWaiting() {
        return waiting;
    }

    public void setWaiting(Date waiting) {
        this.waiting = waiting;
    }

}
