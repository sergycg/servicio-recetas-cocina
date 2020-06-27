package com.recetas.service;

import java.util.List;

import com.recetas.entity.Receta;

public interface IRecetaCocinaService {
	
	public List<Receta> findAll();
	
	public void save (Receta receta);
	
	public Receta findById(Long id);

}
