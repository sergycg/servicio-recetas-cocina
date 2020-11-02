package com.recetas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.recetas.entity.Ingrediente;
import com.recetas.service.IIngredienteService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/ingredientes")
public class IngredientesController {

	@Autowired
	private IIngredienteService ingredienteService;

	@GetMapping()
	public ResponseEntity<?> getIngredientes() {
		List<Ingrediente> ingredientes = ingredienteService.findIngredientes();
		if (ingredientes != null) {
			return new ResponseEntity<>(ingredientes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/pagina")
	public ResponseEntity<?> listarIngredientes(Pageable pageable){
		Page<Ingrediente> ingredientes = ingredienteService.findIngredientes(pageable);
		if (ingredientes != null) {
			return new ResponseEntity<>(ingredientes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getIngredienteById(@PathVariable Long id) {
		Ingrediente ingrediente = ingredienteService.findIngredienteById(id);
		if (ingrediente != null) {
			return new ResponseEntity<>(ingrediente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping()
	public ResponseEntity<?> saveIngrediente(@RequestBody Ingrediente ingrediente) {
		this.ingredienteService.saveIngrediente(ingrediente);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIngrediente(@PathVariable Long id) {
		this.ingredienteService.deleteIngrediente(id);
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrarIngredientes(@PathVariable String term){
		return ResponseEntity.ok(ingredienteService.findByNombre(term));
	}
	
	@GetMapping("/pagina/filtrar/{term}")
	public ResponseEntity<?> filtrarPaginable(Pageable pageable, @PathVariable String term){
		Page<Ingrediente> ingredientes = ingredienteService.findByNombre(term, pageable);
		if (ingredientes != null) {
			return new ResponseEntity<>(ingredientes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
