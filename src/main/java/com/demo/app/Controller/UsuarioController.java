package com.demo.app.Controller;

import com.demo.app.Model.Entity.Usuario;
import com.demo.app.Model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listar(Map<String ,Object>model){
        model.put("user", userService.findAll());
        model.put("title", "Registro");
        return "persona/listar";
    }
    @GetMapping("/form")
    public String crear(Map<String,Object> model){

        Usuario usuario = new Usuario();
        model.put("usuario" , usuario);
        model.put("title", "Formulario");
        return "persona/formulario";
    }
    @PostMapping("/form")
    public String save(@Valid Usuario usuario, BindingResult result, Map<String,Object>model,
                       RedirectAttributes flash, SessionStatus sessionStatus){

        if(result.hasErrors()){
            model.put("title", "Formulario");
            return "persona/formulario";
        }
        userService.save(usuario);
        sessionStatus.setComplete();

        String messages = (usuario.getId() != null) ? "Usuario creado con exito" : "Usuario editado con exito";
        flash.addFlashAttribute("success",messages);

        /*if(usuario.getId() != null){

            flash.addFlashAttribute("success","Usuario creado con exito");
        }else{
            flash.addFlashAttribute("success","Usuario editado con exito");
        }*/

        return "redirect:/user/list";
    }

    @GetMapping("/form/{id}")
    public String edit (@PathVariable("id")Long id , Map<String,Object>model , RedirectAttributes flash){

        Usuario usuario = null;
        if (id > 0) {
            usuario = userService.findOne(id);
            flash.addFlashAttribute("info","Usuario editado con exito");
            if(id == null){
                flash.addFlashAttribute("info","Usuario no puede ser menor que 0");
                return "redirect:/user/list";
            }
        }else{
            flash.addFlashAttribute("info","Usuario no existe en la base de datos");
            return "redirect:/user/list";
        }
        model.put("usuario" , usuario);
        return "persona/formulario";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id,RedirectAttributes flash){

        if(id > 0){
            userService.delete(id);
            flash.addFlashAttribute("warning","Usuario eliminado con exito");
        }
        return "redirect:/user/list";
    }
}
