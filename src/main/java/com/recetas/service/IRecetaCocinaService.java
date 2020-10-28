package com.recetas.service;

import java.util.List;

import com.recetas.entity.Ingrediente;
import com.recetas.entity.Receta;

public interface IRecetaCocinaService {
	
	public List<Receta> findRecetas();
	
	public void saveReceta (Receta receta);
	
	public Receta findRecetaById(Long id);

	public List<Ingrediente> findIngredientes();
	
	public void saveIngrediente (Ingrediente ingrediente);
	
	public Ingrediente findIngredienteById(Long id);
	
	public void deleteReceta(Long id);
	
	public void deleteIngrediente(Long id);

}
