package Spring;


import Baskets.Credentials;
import Baskets.Order;
import Baskets.Orders;


import Baskets.ShoppingCart;
import CheckingThreads.GenerateOrders;
import Goods.BuildingMaterials;
import Goods.Good;
import Goods.Paints;
import Spring.Repositories.OrdersRepository;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
public class MainController {

    private Logger log = LogManager.getLogger(MainController.class);
    private final OrdersRepository ordersRepository;

    @Autowired
    public MainController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }


    @RequestMapping(value = "/",
            params = {"command"},
            method = RequestMethod.GET
    )
    public Orders getAll() {
        log.info("getAll");
        ordersRepository.findAll();
        Orders orders = ordersRepository.getOne(0);
        return orders;
    }

    @RequestMapping(value = "/",
            params = {"command", "order_id"},
            method = RequestMethod.GET
    )
    @ResponseBody
    public String getReadById(@RequestParam("command") String command, @RequestParam("order_id") String id) throws RequestException {
        ordersRepository.findAll();
        Orders orders = ordersRepository.getOne(0);
        Gson gson = new Gson();
        if (command.toLowerCase().equals("readbyid")) {
            log.info("readbyid" + "command=" + command + "id=" + id);
            if (findOrderById(orders, UUID.fromString(id)) != null)
                return gson.toJson(findOrderById(orders, UUID.fromString(id)));
            else
                throw new RequestException("Заказ не найден");
        }


        Good good;
        if (command.toLowerCase().equals("delbyid")) {
            log.info("delbyid" + "command=" + command + "id=" + id);
            for (int i = 0; i < orders.getOrdersList().size(); i++) {
                ShoppingCart shoppingCart = ((Order) orders.getOrdersList().get(i)).getShoppingCart();
                good = shoppingCart.search(UUID.fromString(id));
                if (good != null) {
                    ((Order) orders.getOrdersList().get(i)).getShoppingCart().delete(good);
                    ordersRepository.save(orders);
                    return "0";
                }
            }
            throw new RequestException("нет такого заказа");
        }
        return "2";
    }

    @RequestMapping(value = "/",
            params = {"command", "card_id"},
            method = RequestMethod.GET
    )
    @ResponseBody
    public String addToCard(@RequestParam("card_id") String id) throws RequestException {
        log.info("addToCard" + " id=" + id);
        ordersRepository.findAll();
        Orders orders = ordersRepository.getOne(0);

        Order order = findOrderById(orders, UUID.fromString(id));
        if (order != null) {
            GenerateOrders generateOrders = new GenerateOrders();
            Good good = generateOrders.getRandomGood();
            order.getShoppingCart().add(good);
            orders = saveById(orders, UUID.fromString(id), order);
            ordersRepository.save(orders);

            return good.getId().toString();
        } else {
            throw new RequestException("Нет такого заказа");
        }
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public void getMistake() throws RequestException {
        throw new RequestException("Неправильная команда");
    }

    @RequestMapping(value = "/",
            params = {"fill"},
            method = RequestMethod.GET
    )
    @ResponseBody
    public void fillBase() {
        Orders orders = new Orders();
        Credentials credentials = new Credentials();
        credentials.setId(UUID.randomUUID());
        credentials.setName("Граф");
        credentials.setSurname("Дуку");
        credentials.setPatronymic("Графович");
        credentials.setEmail("graph@imperiamail.com");

        ShoppingCart shoppingCart = new ShoppingCart();
        UUID uuidGraph = credentials.getId();

        shoppingCart.add(new BuildingMaterials());
        shoppingCart.add(new Paints());


        Order order = new Order();
        order.setCredentials(credentials);
        order.setShoppingCart(shoppingCart);
        orders.addOrder(order);
        orders.makeDeal(order);

        Baskets.Credentials credentialsDart = new Baskets.Credentials();
        credentialsDart.setId(UUID.randomUUID());
        credentialsDart.setName("Энакин");
        credentialsDart.setSurname("Скайуокер");
        credentialsDart.setPatronymic("Вейдер");
        credentialsDart.setEmail("dart@imperialmail.com");

        UUID uuidDart = credentialsDart.getId();

        ShoppingCart shoppingCart1 = new ShoppingCart();


        shoppingCart1.add(new BuildingMaterials());
        shoppingCart1.add(new Paints());

        Order order1 = new Order();
        order1.setCredentials(credentialsDart);
        order1.setShoppingCart(shoppingCart);
        orders.addOrder(order1);
        orders.makeDeal(order1);

        ordersRepository.save(orders);
    }

    private Order findOrderById(Orders orders, UUID uuid) {
        for (int i = 0; i < orders.getOrdersList().size(); i++) {
            if (((Order) orders.getOrdersList().get(i)).getCredentials().getId().equals(uuid))
                return (Order) orders.getOrdersList().get(i);
        }
        return null;
    }

    private Orders saveById(Orders orders, UUID id, Order order) {
        for (int i = 0; i < orders.getOrdersList().size(); i++) {
            if (((Order) orders.getOrdersList().get(i)).getCredentials().getId().equals(id)) {
                orders.getOrdersList().remove(i);
                orders.getOrdersList().add(i, order);
            }

        }
        return orders;
    }

}
