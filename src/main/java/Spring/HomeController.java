package Spring;



import Baskets.Order;
import Baskets.Orders;
import Baskets.ShoppingCart;
import CheckingThreads.GenerateOrders;
import DataHandler.ManagerOrderFile;
import DataHandler.ManagerOrderJSON;
import Goods.Good;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;


@RestController
public class HomeController
{
    Orders orders=new Orders<>();
    Logger log= LogManager.getLogger(HomeController.class);

    ManagerOrderFile managerOrderFile=new ManagerOrderFile("base");



    @RequestMapping(value="/",
            params = {"command"},
            method= RequestMethod.GET
    )
    public String getAll() {
        try {
            managerOrderFile.openInput();
        } catch (IOException e) {
            new RequestException(2);
        }

        orders=managerOrderFile.readAll();

        log.info("getAll");
        Gson gson=new Gson();
        StringBuilder stringBuilder=new StringBuilder();

        for(int i=0;i<orders.getOrdersList().size();i++) {
            stringBuilder.append(gson.toJson(orders.getOrdersList().get(i)));
            stringBuilder.append("\n");
        }
        managerOrderFile.closeInput();
        return stringBuilder.toString();
    }

    @RequestMapping(value="/",
    params = {"command","order_id"},
    method=RequestMethod.GET
    )
    @ResponseBody
    public String getReadById(@RequestParam("command") String command,@RequestParam("order_id") String id){

        Gson gson=new Gson();
        boolean flag=false;
        if(command.toLowerCase().equals("readbyid")){
            flag=true;
            try {
                managerOrderFile.openInput();
            } catch (IOException e) {
                new RequestException(2);
            }
            log.info("readbyid");
            Order order=managerOrderFile.readById(UUID.fromString(id));
           managerOrderFile.closeInput();
            return gson.toJson(order);

        }
        if(flag==true)
            new RequestException(1);
        Good good;

        if(command.toLowerCase().equals("delbyid")){
            try {
                managerOrderFile.openInput();
            } catch (IOException e) {
                new RequestException(2);
            }
            log.info("delbyid");
            orders=managerOrderFile.readAll();
            managerOrderFile.closeInput();
            for(int i=0;i<orders.getOrdersList().size();i++) {
                ShoppingCart shoppingCart=((Order)orders.getOrdersList().get(i)).shoppingCart;
                good=shoppingCart.search(UUID.fromString(id));
                if (good!= null) {
                    try {
                        managerOrderFile.openOutput();
                    } catch (IOException e) {
                        new RequestException(2);
                    }
                    ((Order) orders.getOrdersList().get(i)).shoppingCart.delete(good);
                    managerOrderFile.saveAll(orders);
                    managerOrderFile.closeOutput();
                    return "0";
                }
            }
            new RequestException(1);
        }

        return "2";
    }


    @RequestMapping(value="/",
            params = {"command","card_id"},
            method=RequestMethod.GET
    )
    @ResponseBody
    public String addToCard(@RequestParam("card_id") String id){
        log.info("addToCard");
        try {
            managerOrderFile.openInput();
        } catch (IOException e) {
            new RequestException(2);
        }
        Order order=managerOrderFile.readById(UUID.fromString(id));

        managerOrderFile.closeInput();
        GenerateOrders generateOrders=new GenerateOrders();
        Good good=generateOrders.getRandomGood();

        order.shoppingCart.add(good);
        try {
            managerOrderFile.openOutput();
        } catch (IOException e) {
            new RequestException(2);
        }
        managerOrderFile.saveById(UUID.fromString(id),order);


        managerOrderFile.closeOutput();

        return good.id.toString();
    }
    @RequestMapping(value = "/")
    @ResponseBody
    public void getMistake(){
        new RequestException(3);
    }

}