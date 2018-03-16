package Spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class HomeController
{
    @RequestMapping(value="/ex/{id}")
    public String homeInit(Model model, @PathVariable("id")String id) {

        return "id"+id;
    }
}