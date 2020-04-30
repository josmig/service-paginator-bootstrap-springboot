package com.demo.app.Model.Service;

import com.demo.app.Model.Entity.Usuario;

import java.util.List;

public interface UserService {

    List<Usuario> findAll();
    public void save(Usuario usuario);
    public Usuario findOne(Long id);
    public void delete(Long id);
}
