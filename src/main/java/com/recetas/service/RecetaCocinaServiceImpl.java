package com.recetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recetas.dao.IRecetaCocinaDao;
import com.recetas.entity.Paso;
import com.recetas.entity.Receta;
import com.recetas.entity.RecetaIngredientes;

@Service
public class RecetaCocinaServiceImpl implements IRecetaCocinaService {

	@Autowired
	private IRecetaCocinaDao daoRecetaCocina;
	
	@Override
	@Transactional(readOnly = true)
	public List<Receta> findRecetas() {
		return (List<Receta>) daoRecetaCocina.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Receta> findRecetas(Pageable pageable) {
		return daoRecetaCocina.findAll(pageable);
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

}
