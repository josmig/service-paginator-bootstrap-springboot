package com.demo.app.Model.Dao;

import com.demo.app.Model.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{	
	//CrudRepository<Usuario,Long> 
}
