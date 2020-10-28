package com.recetas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.recetas.entity.Ingrediente;
import com.recetas.entity.Receta;


public interface IIngredienteDao extends JpaRepository<Ingrediente, Long> {

//	public List<Ingrediente> findByRecetaId(Long id);
	
}
