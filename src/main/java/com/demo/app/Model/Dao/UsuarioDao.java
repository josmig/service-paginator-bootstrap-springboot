package com.demo.app.Model.Dao;

import com.demo.app.Model.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario,Long> {
}
