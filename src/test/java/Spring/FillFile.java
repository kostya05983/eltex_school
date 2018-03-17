package Spring;

import Baskets.Credentials;
import Baskets.Order;
import Baskets.Orders;
import Baskets.ShoppingCart;
import DataHandler.ManagerOrderFile;
import DataHandler.ManagerOrderJSON;
import Goods.BuildingMaterials;
import Goods.Good;
import Goods.Paints;

import java.util.UUID;

public class FillFile {

    public static void main(String[] args) {
        Orders orders=new Orders();
        Credentials credentials=new Credentials();
        credentials.setId(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"));
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
        credentialsDart.setId(UUID.fromString("38400001-8cf0-11bd-b23e-10b96e4ef00d"));
        credentialsDart.setName("Энакин");
        credentialsDart.setSurname("Скайуокер");
        credentialsDart.setPatronymic("Вейдер");
        credentialsDart.setEmail("dart@imperialmail.com");

        UUID uuidDart=credentialsDart.getId();

        ShoppingCart shoppingCart1=new ShoppingCart();


        shoppingCart1.add(new BuildingMaterials());
        shoppingCart1.add(new Paints());
        //System.out.println(((Good)shoppingCart1.goods.get(0)).id);

        Order order1=new Order();
        order1.credentials=credentialsDart;
        order1.shoppingCart=shoppingCart1;
        orders.addOrder(order1);
        orders.makeDeal(order1);


        ManagerOrderJSON managerOrderJSON=new ManagerOrderJSON("base");
        managerOrderJSON.saveAll(orders);
        managerOrderJSON.closeOutput();

        ManagerOrderFile managerOrderFile=new ManagerOrderFile("base");
        managerOrderFile.saveAll(orders);
        managerOrderFile.closeOutput();


    }
}
