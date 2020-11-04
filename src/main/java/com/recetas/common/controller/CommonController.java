package com.recetas.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
		this.service.save(e);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.service.delete(id);
	}

}
