package DataHandler;

import Baskets.Order;
import Baskets.Orders;

import java.util.UUID;

public interface IOrder {


    Order readById(UUID i);
    void saveById(UUID i, Order order);
    Orders readAll();
    void saveAll(Orders orders);
}
