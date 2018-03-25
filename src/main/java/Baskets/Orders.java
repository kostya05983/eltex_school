package Baskets;

import Goods.Good;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;
@Entity
@Table(name="Orders")
public class Orders <T extends Order> implements Serializable{

    @Id
    @Column(name="ordersList")
    private LinkedList<Order> ordersList=new LinkedList();
    private LinkedHashMap<Date, Credentials> linkedHashMap=new LinkedHashMap();


    public void setOrdersList(LinkedList<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public LinkedList<Order> getOrdersList() {
        return ordersList;
    }

    public void makeDeal(Order order){
        Random rn=new Random(System.currentTimeMillis());

        order.setCreation(new Date(System.currentTimeMillis()));
        order.setWaiting(new Date(System.currentTimeMillis()));
        order.getWaiting().setTime(System.currentTimeMillis()+(long)(rn.nextDouble()*10000));

        linkedHashMap.put(order.getCreation(),order.getCredentials());
    }

    public  void addOrder(T obj){
        ordersList.add(obj);
    }

    public  void deleteOrder(T obj){
        ordersList.remove(obj);
    }

    public void checkOrders(){
        Date now=new Date(System.currentTimeMillis());

    for(int i=0;i<ordersList.size();i++)
        if(ordersList.get(i).isStatus()||(ordersList.get(i).getWaiting().before(now)))
            Good.count-=ordersList.remove(i).getShoppingCart().goods.size();

    }

    public void showOrdersList(){
        String show="";
        String buf;
        for(Order order:ordersList){
            if(order.isStatus())
                buf="обработан";
            else
                buf="не обработан";

            if(linkedHashMap!=null&&linkedHashMap.size()!=0)
            show+=linkedHashMap.get(order.getCreation()).toString()+"\n";
            else show+=order.getCredentials().toString()+"\n";
            show+="Время оформления:"+order.getCreation()+" Время ожидания:"+order.getWaiting()+" Статус заказа:"+buf+"\n"+order.getShoppingCart().toString()+" \n";
        }
        System.out.println(show);
    }
}
