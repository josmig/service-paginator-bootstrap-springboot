package com.demo.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {

    @RequestMapping({"/","home"})
    public String index(Model mode){
        mode.addAttribute("title","Home");
        return "index";
    }
}
