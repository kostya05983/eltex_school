import Baskets.Credentials;
import Baskets.Order;
import Baskets.Orders;
import Baskets.ShoppingCart;
import DataHandler.ManagerOrderJSON;
import Goods.BuildingMaterials;
import Goods.Paints;

import java.util.UUID;

public class Lab5 {
    public static void main(String[] args) {
              Orders orders=new Orders();
        Credentials credentials=new Credentials();
        credentials.setId(UUID.randomUUID());
        credentials.setName("Граф");
        credentials.setSurname("Дуку");
        credentials.setPatronymic("Графович");
        credentials.setEmail("graph@imperiamail.com");

        ShoppingCart shoppingCart=new ShoppingCart();
        UUID uuidGraph=credentials.getId();

        shoppingCart.add(new BuildingMaterials());
        shoppingCart.add(new Paints());


        Order order=new Order();
        order.credentials=credentials;
        order.shoppingCart=shoppingCart;
        orders.addOrder(order);
        orders.makeDeal(order);

        Baskets.Credentials credentialsDart=new Baskets.Credentials();
        credentialsDart.setId(UUID.randomUUID());
        credentialsDart.setName("Энакин");
        credentialsDart.setSurname("Скайуокер");
        credentialsDart.setPatronymic("Вейдер");
        credentialsDart.setEmail("dart@imperialmail.com");

        UUID uuidDart=credentialsDart.getId();

        ShoppingCart shoppingCart1=new ShoppingCart();


        shoppingCart1.add(new BuildingMaterials());
        shoppingCart1.add(new Paints());

        Order order1=new Order();
        order1.credentials=credentialsDart;
        order1.shoppingCart=shoppingCart;
        orders.addOrder(order1);
        orders.makeDeal(order1);

//        ManagerOrderFile managerOrderFile=new ManagerOrderFile("/home/kostya05983/IdeaProjects/Eltex_School/src/main/resources/test");
//
//        managerOrderFile.saveAll(orders);
//
//        managerOrderFile.openOutput();
//
////        Orders orders1;
////        orders1=managerOrderFile.readAll();
//
//        //orders1.showOrdersList();
//
//        //System.out.println(managerOrderFile.readById(uuidDart).toString());
//
//
//        orders=managerOrderFile.readAll();
//        orders.showOrdersList();

        ManagerOrderJSON managerOrderJSON=new ManagerOrderJSON("/home/kostya05983/IdeaProjects/Eltex_School/src/main/resources/test");
        managerOrderJSON.saveAll(orders);
        managerOrderJSON.closeOutput();

        managerOrderJSON.openInput();
        System.out.println( managerOrderJSON.readById(uuidDart));
//        managerOrderJSON.openBufferedWriter();
//       managerOrderJSON.saveById(uuidDart,order);
//        managerOrderJSON.closeBufferedWriter();

        managerOrderJSON.openInput();
//
        orders=managerOrderJSON.readAll();
//
        orders.showOrdersList();
    }
//


}
