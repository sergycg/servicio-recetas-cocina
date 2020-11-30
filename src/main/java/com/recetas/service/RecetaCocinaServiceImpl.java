package com.recetas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recetas.common.service.CommonServiceImpl;
import com.recetas.dao.RecetaCocinaDao;
import com.recetas.entity.Paso;
import com.recetas.entity.Receta;
import com.recetas.entity.RecetaIngredientes;

@Service
public class RecetaCocinaServiceImpl extends CommonServiceImpl<Receta, RecetaCocinaDao> implements RecetaCocinaService {

	@Autowired
	private RecetaCocinaDao recetaCocinaDao;
	
	@Override
	@Transactional
	public Receta save (Receta receta) {
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
		return recetaCocinaDao.save(receta);	
	}

	@Override
	@Transactional(readOnly = true)
	public List<Receta> findLikeNombre(String term) {
		return recetaCocinaDao.findLikeNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Receta> findLikeNombre(String term, Pageable pageable) {
		return recetaCocinaDao.findLikeNombre(term, pageable);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Receta> findByNombre(String nombre) {
		return repository.findByNombre(nombre);
    }
	
}
