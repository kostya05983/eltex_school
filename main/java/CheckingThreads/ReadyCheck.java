package CheckingThreads;


import Baskets.Order;
import Baskets.Orders;

import java.util.LinkedList;
import java.util.Random;

public class ReadyCheck extends ACheck {
private LinkedList<Order> ordersList;


    public void setIsAlive(boolean isActive){
        this.isActive=isActive;
    }

    public ReadyCheck(Orders orders){
        super(orders);
        this.ordersList=orders.getOrdersList();
    }

    @Override
    public synchronized void run() {
        Random rn=new Random(System.currentTimeMillis());
        while (isActive){
            ordersList=orders.getOrdersList();
            try {
                Thread.sleep((long)(100000*rn.nextDouble()));

            for(int i=0;i<ordersList.size();i++)
                if(ordersList.get(i).status)
                ordersList.remove(i);
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (NullPointerException e){

            }
        }

    }

}
