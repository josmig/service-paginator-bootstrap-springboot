package com.demo.app.Controller;

import com.demo.app.Model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listar(Map<String ,Object>model){
        model.put("user", userService.findAll());
        return "persona/listar";
    }
}
