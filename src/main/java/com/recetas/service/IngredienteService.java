package com.recetas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.recetas.common.service.CommonService;
import com.recetas.entity.Ingrediente;

public interface IngredienteService extends CommonService<Ingrediente>{
	
	public Page<Ingrediente> findByNombre(String term, Pageable pageable);
	
	public List<Ingrediente> findByNombre(String term);

}
