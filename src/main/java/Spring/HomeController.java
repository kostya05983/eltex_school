package Spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RestController
public class HomeController
{
    @RequestMapping(value="/",
            params = {"command"},
            method= RequestMethod.GET
    )
    public String getAll(Model model) {//TODO вернуть все заказы в виде JSON

        return "все заказы";
    }

    @RequestMapping(value="/",
    params = {"command","order_id"},
    method=RequestMethod.GET
    )
    @ResponseBody
    public String getReadById(@RequestParam("command") String command,@RequestParam("order_id") String id){//TODO верунть по ид заказ в виде json
        return "заказ"+command;
    }//TODO удаляем товар с конкретным идентификатором написать свой класс обработчик код 1 нет такого заказа,кд 2 файл поврежден, 3-неправильная команда


    @RequestMapping(value="/",
            params = {"command","card_id"},
            method=RequestMethod.GET
    )
    @ResponseBody
    public String addToCard(@RequestParam("card_id") String id){//TODO генерируем товар и добавляем в корзиун с конкретным id
        return "сгенерирована товар";
    }


}