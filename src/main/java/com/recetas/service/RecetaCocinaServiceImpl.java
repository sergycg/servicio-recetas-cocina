package com.recetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recetas.dao.IIngredienteDao;
import com.recetas.dao.IRecetaCocinaDao;
import com.recetas.entity.Ingrediente;
import com.recetas.entity.Paso;
import com.recetas.entity.Receta;
import com.recetas.entity.RecetaIngredientes;

@Service
public class RecetaCocinaServiceImpl implements IRecetaCocinaService {

	@Autowired
	private IRecetaCocinaDao daoRecetaCocina;
	@Autowired
	private IIngredienteDao daoIngrediente;
	
	@Override
	@Transactional(readOnly = true)
	public List<Receta> findRecetas() {
		return (List<Receta>) daoRecetaCocina.findAll();
	}

	@Override
	@Transactional
	public void saveReceta (Receta receta) {
		if (receta.getIngredientes()!=null) {
			for (RecetaIngredientes ingrediente : receta.getIngredientes()) {
				ingrediente.setReceta(receta);
			}
		}
		if (receta.getPasos()!=null) {
			for (Paso paso : receta.getPasos()) {
				paso.setReceta(receta);
			}
		}
		daoRecetaCocina.save(receta);	
	}

	@Override
	@Transactional
	public void deleteReceta(Long id) {
		daoRecetaCocina.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Receta findRecetaById(Long id) {
		return daoRecetaCocina.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ingrediente> findIngredientes() {
		return (List<Ingrediente>) daoIngrediente.findAll();
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
}
