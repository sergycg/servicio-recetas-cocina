package com.recetas.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonService<E> {
	
	public List<E> findAll();

	public Page<E> findAll(Pageable pageable);
	
	public Page<E> findAllPageAndSortByAsc(Integer pageNo, Integer pageSize, String sortBy);
	
	public Page<E> findAllPageAndSortByDesc(Integer pageNo, Integer pageSize, String sortBy);
	
	public List<E> findAllSortByAsc(String sortBy);
	
	public List<E> findAllSortByDesc(String sortBy);

	public E findById(Long id);
	
	public E save (E ingrediente);
	
	public void delete(Long id);

}
