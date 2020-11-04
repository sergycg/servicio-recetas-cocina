package com.recetas.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.recetas.common.service.CommonService;
import com.recetas.entity.Receta;

public interface RecetaCocinaService extends CommonService<Receta>{
	
	public Page<Receta> findByNombre(String term, Pageable pageable);
	
	public List<Receta> findByNombre(String term);

}
