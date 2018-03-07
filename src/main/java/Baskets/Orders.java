package Baskets;

import Goods.Good;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

public class Orders <T extends Order> implements Serializable{
    protected   LinkedList<Order> ordersList=new LinkedList();
    protected LinkedHashMap<Date, Credentials> linkedHashMap=new LinkedHashMap();


    public void setOrdersList(LinkedList<Order> ordersList) {
        this.ordersList = ordersList;
    }

    public LinkedList<Order> getOrdersList() {
        return ordersList;
    }

    public void makeDeal(Order order){
        Random rn=new Random(System.currentTimeMillis());

        order.creation=new Date(System.currentTimeMillis());
        order.waiting=new Date(System.currentTimeMillis());
        order.waiting.setTime(System.currentTimeMillis()+(long)(rn.nextDouble()*10000));

        linkedHashMap.put(order.creation,order.credentials);
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
        if(ordersList.get(i).status||(ordersList.get(i).waiting.before(now)))
            Good.count-=ordersList.remove(i).shoppingCart.goods.size();

    }

    public void showOrdersList(){
        String show="";
        String buf;
        for(Order order:ordersList){
            if(order.status)
                buf="обработан";
            else
                buf="не обработан";

            if(linkedHashMap!=null&&linkedHashMap.size()!=0)
            show+=linkedHashMap.get(order.creation).toString()+"\n";
            else show+=order.credentials.toString()+"\n";
            show+="Время оформления:"+order.creation+" Время ожидания:"+order.waiting+" Статус заказа:"+buf+"\n"+order.shoppingCart.toString()+" \n";
        }
        System.out.println(show);
    }
    //
}
