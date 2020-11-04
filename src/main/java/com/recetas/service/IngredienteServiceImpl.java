package com.recetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recetas.common.service.CommonServiceImpl;
import com.recetas.dao.IngredienteDao;
import com.recetas.entity.Ingrediente;

@Service
public class IngredienteServiceImpl extends CommonServiceImpl<Ingrediente, IngredienteDao> implements IngredienteService {

	@Autowired
	private IngredienteDao ingredienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ingrediente> findByNombre(String term) {
		return ingredienteDao.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Ingrediente> findByNombre(String term, Pageable pageable) {
		return ingredienteDao.findByNombre(term, pageable);
	}

}
