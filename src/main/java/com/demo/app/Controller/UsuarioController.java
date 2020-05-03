package com.demo.app.Controller;

import com.demo.app.Model.Entity.Usuario;
import com.demo.app.Model.Service.IUserService;
import com.demo.app.Model.Service.UserService;
import com.demo.app.Util.Paginator.PageRender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private IUserService service;

    @GetMapping("/list")
    public String listar(@RequestParam(name = "page" ,defaultValue = "0") int page,Map<String ,Object>model){
    	
    	//aca le decimos cuantos registros por pagina se mostrara
    	Pageable pageRequest = PageRequest.of(page, 5);
    	Page<Usuario> usuarios = service.findAll(pageRequest);
    	PageRender<Usuario> pageRender= new PageRender<>("/user/list", usuarios);
        
    	model.put("user", usuarios);        
        model.put("page", pageRender);
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
        
        service.save(usuario);
        sessionStatus.setComplete();
        String messages = (usuario.getId() != null) ? "Usuario editado con exito" : "Usuario creado con exito";
        flash.addFlashAttribute("success",messages);

        return "redirect:/user/list";
    }

    @GetMapping("/form/{id}")
    public String edit (@PathVariable(name = "id")Long id , Map<String,Object>model , RedirectAttributes flash){


        Usuario usu = null;
        if(id > 0){
        	usu = service.findOne(id);
            if(usu == null){
                flash.addFlashAttribute("error","El ID del usuario no existe en la BD");
                return "redirect:/user/list";
            }
        }else{
            flash.addFlashAttribute("error","El ID del usuario no puede ser 0");
            return "redirect:/user/list";
        }
        model.put("title","Editar Usuario");
        model.put("usuario",usu);
        return "persona/formulario";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id")Long id,RedirectAttributes flash){

        if(id > 0){
        	service.delete(id);
            flash.addFlashAttribute("warning","Usuario eliminado con exito");
        }
        return "redirect:/user/list";
    }
}
