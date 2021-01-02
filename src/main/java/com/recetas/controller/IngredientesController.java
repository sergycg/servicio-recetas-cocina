package com.recetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.common.controller.CommonController;
import com.recetas.entity.Ingrediente;
import com.recetas.service.IngredienteService;

//@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/ingredientes")
public class IngredientesController extends CommonController<Ingrediente, IngredienteService>{

	@Autowired
	private IngredienteService ingredienteService;

	@GetMapping()
	public ResponseEntity<?> getIngredientes() {
		List<Ingrediente> ingredientes = ingredienteService.findAllSortByAsc("nombre");
		if (ingredientes != null) {
			return new ResponseEntity<>(ingredientes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrarIngredientes(@PathVariable String term){
		return ResponseEntity.ok(ingredienteService.findLikeNombre(term));
	}
	
	@GetMapping("/pagina/filtrar/{term}")
	public ResponseEntity<?> filtrarPaginable(Pageable pageable, @PathVariable String term){
		Page<Ingrediente> ingredientes = ingredienteService.findLikeNombre(term, pageable);
		if (ingredientes != null) {
			return new ResponseEntity<>(ingredientes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> getBynombre(@PathVariable String nombre) {
		List<Ingrediente> ingredientes = ingredienteService.findByNombre(nombre);
		if (ingredientes != null) {
			return new ResponseEntity<>(ingredientes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
