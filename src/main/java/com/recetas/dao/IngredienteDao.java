package com.recetas.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recetas.entity.Ingrediente;


public interface IngredienteDao extends JpaRepository<Ingrediente, Long> {

	@Query("select e from Ingrediente e where e.nombre like %?1% order by e.nombre asc")
	public List<Ingrediente> findByNombre(String term);

	@Query("select e from Ingrediente e where e.nombre like %:nombre% order by e.nombre asc")
	Page<Ingrediente> findByNombre(@Param("nombre") String nombre, Pageable pageable);
	
	public List<Ingrediente> findAllByOrderByNombreAsc(); 
	
	public Page<Ingrediente> findAllByOrderByNombreAsc(Pageable pageable); 
}
