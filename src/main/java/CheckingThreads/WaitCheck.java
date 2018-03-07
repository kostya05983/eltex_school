package CheckingThreads;


import Baskets.Order;
import Baskets.Orders;

import java.util.LinkedList;
import java.util.Random;

public class WaitCheck extends ACheck {
    private  LinkedList<Order> ordersList;


    public void setIsAlive(boolean isActive){
        this.isActive=isActive;
    }

    public WaitCheck(Orders orders){
        super(orders);
        this.ordersList=orders.getOrdersList();
    }

    @Override
    public  synchronized void run() {
        Random rn=new Random(System.currentTimeMillis());

        while(isActive) {
            ordersList=orders.getOrdersList();
            try {
                Thread.sleep((long)(10000*rn.nextDouble()));

            for (int i = 0; i <ordersList.size();i++)
                if(!ordersList.get(i).status)
                    ordersList.get(i).status=true;
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (NullPointerException e){

            }
        }

    }
}
