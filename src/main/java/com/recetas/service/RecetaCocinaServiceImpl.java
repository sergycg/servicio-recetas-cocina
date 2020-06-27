package com.recetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recetas.dao.IRecetaCocinaDao;
import com.recetas.entity.Receta;

@Service
public class RecetaCocinaServiceImpl implements IRecetaCocinaService {

	@Autowired
	private IRecetaCocinaDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Receta> findAll() {
		return (List<Receta>) dao.findAll();
	}

	@Override
	@Transactional
	public void save (Receta receta) {
		dao.save(receta);	
	}

	@Override
	@Transactional(readOnly = true)
	public Receta findById(Long id) {
		return dao.findById(id).orElse(null);
	}

}
