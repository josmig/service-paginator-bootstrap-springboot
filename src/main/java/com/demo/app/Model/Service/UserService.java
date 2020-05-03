package com.demo.app.Model.Service;

import com.demo.app.Model.Entity.Usuario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    List<Usuario> findAll();
    
    Page<Usuario> findAll(Pageable pageable);
    
    public void save(Usuario usuario);
    public Usuario findOne(Long id);
    public void delete(Long id);
}
