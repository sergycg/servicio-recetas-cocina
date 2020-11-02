package com.recetas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.recetas.entity.Receta;

public interface IRecetaCocinaService {
	
	public List<Receta> findRecetas();
	
	public Page<Receta> findRecetas(Pageable pageable);
	
	public void saveReceta (Receta receta);
	
	public Receta findRecetaById(Long id);

	public void deleteReceta(Long id);

}
