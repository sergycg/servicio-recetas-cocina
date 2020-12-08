package com.recetas.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.recetas.common.service.CommonService;

public class CommonController <E, S extends CommonService<E>> {

	@Autowired
	private S service;

	@GetMapping("/pagina")
	public ResponseEntity<?> listarPaginas(Pageable pageable){
		Page<E> e = service.findAll(pageable);
		if (e != null) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		E e = service.findById(id);
		if (e != null) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestBody E e) {
		E newE = this.service.save(e);
		return new ResponseEntity<>(newE, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			this.service.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
	}

}
