package Spring;



import Baskets.Order;
import Baskets.Orders;
import Baskets.ShoppingCart;
import CheckingThreads.GenerateOrders;
import DataHandler.ManagerOrderFile;
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
    private Orders orders=new Orders<>();
    private  Logger log= LogManager.getLogger(HomeController.class);

    private ManagerOrderFile managerOrderFile=new ManagerOrderFile("base");



    @RequestMapping(value="/",
            params = {"command"},
            method= RequestMethod.GET
    )
    public String getAll() throws RequestException {
        try {
            managerOrderFile.openInput();
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
        } catch (IOException e) {
           throw  new RequestException("Файл не найден");
        }


    }

    @RequestMapping(value="/",
    params = {"command","order_id"},
    method=RequestMethod.GET
    )
    @ResponseBody
    public String getReadById(@RequestParam("command") String command,@RequestParam("order_id") String id) throws RequestException {
        Gson gson=new Gson();
        boolean flag=false;
        if(command.toLowerCase().equals("readbyid")){
            flag=true;
            try {
                managerOrderFile.openInput();
                log.info("readbyid");
                Order order=managerOrderFile.readById(UUID.fromString(id));
                managerOrderFile.closeInput();
                if(order!=null)
                    return gson.toJson(order);
            } catch (IOException e) {
               throw new RequestException("Файл поврежден");
            }
        }
        if(flag)
            throw new RequestException("заказ не найден");


        Good good;
        if(command.toLowerCase().equals("delbyid")){
            try {
                managerOrderFile.openInput();
                log.info("delbyid");
                orders=managerOrderFile.readAll();
                managerOrderFile.closeInput();
            } catch (IOException e) {
              throw new RequestException("Файл поврежден");
            }
            for(int i=0;i<orders.getOrdersList().size();i++) {
                ShoppingCart shoppingCart=((Order)orders.getOrdersList().get(i)).shoppingCart;
                good=shoppingCart.search(UUID.fromString(id));
                if (good!= null) {
                    try {
                        managerOrderFile.openOutput();
                        ((Order) orders.getOrdersList().get(i)).shoppingCart.delete(good);
                        managerOrderFile.saveAll(orders);
                        managerOrderFile.closeOutput();
                        return "0";
                    } catch (IOException e) {
                    throw  new RequestException("Файл поврежден");
                    }
                }
            }
           throw new RequestException("нет такого заказа");
        }

        return "2";
    }


    @RequestMapping(value="/",
            params = {"command","card_id"},
            method=RequestMethod.GET
    )
    @ResponseBody
    public String addToCard(@RequestParam("card_id") String id) throws RequestException {
        log.info("addToCard");
        try {
            managerOrderFile.openInput();
        } catch (IOException e) {
            throw new RequestException("Файл повреждлен");
        }
        Order order=managerOrderFile.readById(UUID.fromString(id));

        managerOrderFile.closeInput();
        GenerateOrders generateOrders=new GenerateOrders();
        Good good=generateOrders.getRandomGood();

        order.shoppingCart.add(good);
        try {
            managerOrderFile.openOutput();
            managerOrderFile.openInput();
            managerOrderFile.saveById(UUID.fromString(id),order);
            managerOrderFile.closeOutput();
            managerOrderFile.closeInput();
        } catch (IOException e) {
          throw new RequestException("Файл поврежден");
        }

        return good.id.toString();
    }
    @RequestMapping(value = "/")
    @ResponseBody
    public void getMistake() throws RequestException {
     throw new RequestException("Неправильная команда");
    }

}