package com.recetas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.recetas.entity.Ingrediente;

public interface IIngredienteService {
	
	public List<Ingrediente> findIngredientes();
	
	public Page<Ingrediente> findIngredientes(Pageable pageable);
	
	public Page<Ingrediente> findByNombre(String term, Pageable pageable);
	
	public List<Ingrediente> findByNombre(String term);

	public Ingrediente findIngredienteById(Long id);
	
	public void saveIngrediente (Ingrediente ingrediente);
	
	public void deleteIngrediente(Long id);

}
