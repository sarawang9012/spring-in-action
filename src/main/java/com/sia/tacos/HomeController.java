package com.sia.tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName: HomeController
 * Package: com.sia.tacos
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/12/12 11:57
 * @Version 1.0
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        System.out.println("still not working,why?");

        return "home";
    }

}
