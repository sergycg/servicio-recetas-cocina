package com.recetas.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl <E, R extends JpaRepository<E, Long>> implements CommonService<E>  {

	@Autowired
	protected R repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<E> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<E> findAllSortByAsc(String sortBy) {
		Sort sortOrder = Sort.by(sortBy).ascending(); 
		return repository.findAll(sortOrder);
    }

	@Override
	@Transactional(readOnly = true)
	public List<E> findAllSortByDesc(String sortBy) {
		Sort sortOrder = Sort.by(sortBy).descending(); 
		return repository.findAll(sortOrder);
    }

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAllPageAndSortByAsc(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        return repository.findAll(paging);
    }
	
	@Override
	@Transactional(readOnly = true)
	public Page<E> findAllPageAndSortByDesc(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return repository.findAll(paging);
    }
	
	@Override
	@Transactional
	public E save (E ingrediente) {
		return repository.save(ingrediente);	
	}

	@Override
	@Transactional(readOnly = true)
	public E findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}


}
