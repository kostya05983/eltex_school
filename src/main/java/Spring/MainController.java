package Spring;


import Baskets.Credentials;
import Baskets.Order;
import Baskets.Orders;
import Goods.BuildingMaterials;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



public class MainController {
    //private Orders orders=new Orders<>();
    private Logger log= LogManager.getLogger(MainController.class);

    //private ManagerOrderFile managerOrderFile=new ManagerOrderFile("base");
//    @Autowired
//    private OrdersRepository ordersRepository;

   // private OrderRepository orderRepository;

    @RequestMapping(value="/",
            params = {"command"},
            method= RequestMethod.GET
    )
    public String getAll() throws RequestException {
        Orders orders=new Orders();
        Order order=new Order();
        order.getShoppingCart().add(new BuildingMaterials());
        order.setCredentials(new Credentials());
        orders.addOrder(order);

        //orderRepository.save(order);
        //ordersRepository.save(orders);
       return null;

    }

    @RequestMapping(value="/",
            params = {"command","order_id"},
            method=RequestMethod.GET
    )
    @ResponseBody
    public String getReadById(@RequestParam("command") String command, @RequestParam("order_id") String id) throws RequestException {
       return null;
    }


    @RequestMapping(value="/",
            params = {"command","card_id"},
            method=RequestMethod.GET
    )
    @ResponseBody
    public String addToCard(@RequestParam("card_id") String id) throws RequestException {
       return null;
    }
    @RequestMapping(value = "/")
    @ResponseBody
    public void getMistake() throws RequestException {
        throw new RequestException("Неправильная команда");
    }

}
