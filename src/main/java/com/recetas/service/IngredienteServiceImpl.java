package com.recetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recetas.dao.IIngredienteDao;
import com.recetas.entity.Ingrediente;

@Service
public class IngredienteServiceImpl implements IIngredienteService {

	@Autowired
	private IIngredienteDao daoIngrediente;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ingrediente> findIngredientes() {
		return (List<Ingrediente>) daoIngrediente.findAllByOrderByNombreAsc();
	}

	@Override
	@Transactional
	public void saveIngrediente (Ingrediente ingrediente) {
		daoIngrediente.save(ingrediente);	
	}

	@Override
	@Transactional(readOnly = true)
	public Ingrediente findIngredienteById(Long id) {
		return daoIngrediente.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void deleteIngrediente(Long id) {
		daoIngrediente.deleteById(id);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Page<Ingrediente> findIngredientes(Pageable pageable) {
		return daoIngrediente.findAllByOrderByNombreAsc(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ingrediente> findByNombre(String term) {
		return daoIngrediente.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Ingrediente> findByNombre(String term, Pageable pageable) {
		return daoIngrediente.findByNombre(term, pageable);
	}

}
