package Baskets;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    public boolean status;
    public ShoppingCart shoppingCart;
    public Credentials credentials;
    public Date creation;
    public Date waiting;

    public Order(){
        this.status=false;
        this.shoppingCart=new ShoppingCart();
        this.credentials=new Credentials();
    }

    public String toString(){
        return "статус="+status+" время создания:"+creation+"Время ожидания:"+waiting+"\n"+credentials+"\n"+shoppingCart;
    }
    //
}
