package CheckingThreads;

import Baskets.Credentials;
import Baskets.Order;
import Baskets.Orders;
import Baskets.ShoppingCart;
import Goods.BuildingMaterials;
import Goods.Good;
import Goods.Instruments;
import Goods.Paints;

import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;

public class GenerateOrders extends Thread{
    private LinkedList<Order> ordersList;
    private boolean isActive=true;

    public void setIsAlive(boolean isActive){
        this.isActive=isActive;
    }

    public  GenerateOrders(Orders orders){
        this.ordersList=orders.getOrdersList();
    }

    @Override
    public  void run() {
        Random random=new Random(System.currentTimeMillis());
        Good data;
        ShoppingCart shoppingCart;
        Order order;
        Credentials credentials;
        int amountGoods;

        while(isActive) {
            amountGoods=(int)(10*random.nextDouble()+1);
            shoppingCart=new ShoppingCart();
            while (amountGoods > 0) {
                int i = random.nextInt(10000);
                if (i % 13 == 0) {
                    data = new BuildingMaterials();
                    shoppingCart.add(data);
                    continue;
                }
                if (i % 7 == 0) {
                    data = new Instruments();
                    shoppingCart.add(data);
                    continue;
                }
                data = new Paints();
                shoppingCart.add(data);
                amountGoods--;
            }
            credentials=new Credentials();
            credentials.setId(UUID.randomUUID());
            credentials.setEmail("randomMail"+random.nextInt(10000)+"@imperia");
            credentials.setName("RandomName"+random.nextInt(10000));
            credentials.setSurname("RandomSurname"+random.nextInt(10000));
            credentials.setPatronymic("RandomPatronomyc"+random.nextInt(10000));

            order=new Order();
            order.shoppingCart=shoppingCart;
            order.credentials=credentials;
            ordersList.add(order);
        }
        }
    }
